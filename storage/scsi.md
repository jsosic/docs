# SCSI

## Rescan scsi bus

When you attach new drive, Linux will usually not see it until
you rescan the bus:

```
for i in /sys/class/scsi_host/*; do echo "- - -" > $i/scan; done
```

## Expand existing disk

```
for i in /sys/class/scsi_device/*; do echo 1 > $i/device/rescan; done
```
