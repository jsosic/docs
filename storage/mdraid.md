# MD raid

## Replace disk

Mark disk as failed:

```
mdadm --manage /dev/md0 --fail /dev/sdb1
```

Remove disk from array:

```
mdadm --manage /dev/md0 --remove /dev/sdb1
```

Add a replacement disk:

```
mdadm --manage /dev/md0 --add /dev/sdb1
```

## Manipulate an array

Add more drives to an array:

```
mdadm --add /dev/md0 /dev/sde
mdadm --grow /dev/md0 --raid-devices=5
```
