# Hardware

## MegaRaid

Check SMART status of member of an array behind MegaRaid controller.

```
smartctl -a -d sat+megaraid,0 /dev/sda
```

Note: `sda` represents VirtualDrive, while `0` is the number of 
physical slot/drive in an array.
