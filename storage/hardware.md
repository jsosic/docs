# Hardware

## Block device sector size

Get physical block (sector) size:

```
blockdev --getpbsz /dev/sda
```

or:

```
cat /sys/class/block/sda/queue/physical_block_size
```

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

## Secure erase SSD

```
hdparm --security-set-pass <master_password> /dev/sdb
hdparm --user-master u --security-set-pass <user_password> /dev/sdb
hdparm --user-master u --security-erase-enhanced <user_password> /dev/sdb
```
