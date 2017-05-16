# XenServer

## How to add static route

First identify the name of the interface you want to add static route to:

```
# ip addr list
```
In my specific case, I wanted to add route to `xapi0`.
Now, lets grab UUID:

```
xe network-list bridge=xapi0 | grep uuid
```

Finally use UUID to set static route:

```
xe network-param-set uuid=<Network UUID> other-config:static-routes=<network>/<bitmask>/<gateway IP>
```

Check if everything is set ok:

```
# xe network-param-get uuid=<Network UUID> param-name=other-config
static-routes: <network>/<bitmask>/<gateway IP>; automatic: true
```

Reboot to be safe, and recheck with `ip route show`.


## Convert guest from HVM to PV

Connect to the console or XenServer host.

Retrieve the UUID of the virtual machine:

```
xe vm-list name-label=<vm_label> params=uuid
```

Clear the HVM boot mode:

```
xe vm-param-set uuid=<vm uuid> HVM-boot-policy=””
```

Set pygrub as the boot loader:

```
xe vm-param-set uuid=<vm uuid> PV-bootloader=pygrub
```

Set the display arguments:

```
xe vm-param-set uuid=<vm uuid> PV-args="console=tty0 xencons=tty"
```

Note: other possible options are: `console=hvc0 xencons=hvc` or `console=tty0` or `console=hvc0`

Find the UUID of the interface of the virtual disk:

```
xe vm-disk-list uuid=<vm uuid>
```

Set the disk device as bootable:

```
xe vbd-param-set uuid=<vbd uuid> bootable=true
```

The virtual machine should now boot paravirtualized using a Xen aware kernel.
When booting the virtual machine, it should start up in text-mode with the
high-speed PV kernel. If the virtual machine fails to boot, the most likely
cause is an incorrect grub configuration; run the xe-edit-bootloader script
(`xe-edit-bootloader -n <vm_label>`) at the XenServer host console to edit
the `grub.conf` of the virtual machine until it boots.

Note: If the virtual machine boots and mouse and keyboard controls do not
work properly, closing and re-opening XenCenter generally resolves this issue.
If the issue is still not resolved, try other console settings for PV-args,
being sure to reboot the virtual machine and close and re-open XenCenter between
each setting change.
