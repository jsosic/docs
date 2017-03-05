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
```
xe vm-param-set uuid=19cf733a-eedf-026e-3d02-fad4b4bb0c7a PV-bootloader=pygrub
```
