# Hardware

## Disk serial number

```
$ udevadm info --query=all --name=/dev/sde | grep ID_SERIAL
E: ID_SERIAL=Hitachi_HDT721032SLA360_STF202ML2Z992P
E: ID_SERIAL_SHORT=STF202ML2Z992P
```

## MegaRaid

Check SMART status of member of an array behind MegaRaid controller.

```
smartctl -a -d sat+megaraid,0 /dev/sda
```

Note: `sda` represents VirtualDrive, while `0` is the number of 
physical slot/drive in an array.
