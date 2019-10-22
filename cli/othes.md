# Others

## Seq

Format output with zeroes when using `seq`:

```
seq --format='%02g' 1 20
```

## Java

Check default values of Initial and Max Heap (displayed in bytes):

```
java -XX:+PrintFlagsFinal -version | egrep '(Initial|Max)HeapSize'
```

## Chroot a system from rescue image

If you need to rescue a system from live image, you can chroot
in the following way:

```
mount /dev/sda2 /mnt/sysimage
mount /dev/sda1 /mnt/sysimage/boot

mount --bind /dev /mnt/dev &&
mount --bind /dev/pts /mnt/dev/pts &&
mount --bind /proc /mnt/proc &&
mount --bind /sys /mnt/sys

chroot /mnt/sysimage
```

Note: replace `/dev/sda` with appropriate devices.


## GoAccess report

```
cat access_log | goaccess -a --date-spec=hr -o html --log-format COMBINED - > report.html
```
