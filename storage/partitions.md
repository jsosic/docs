# Partitions

## Copy partition schema

Dump layout of sda to a file, replace `sda` entries with `sdb` and
import on sdb:

```
sfdisk -d /dev/sda > /tmp/partitions
sed -i 's/sda/sdb/g' /tmp/partitions
sfdisk /dev/sdb < /tmp/partitions
```

Copy partition schema directly from sda to sdb in one step:

```
sfdisk -d /dev/sda | sfdisk /dev/sdb
```

In case of a GPT partition, `sgdisk` utility should be used:

```
sgdisk --backup=table /dev/sda
sgdisk --load-backup=table /dev/sdb
sgdisk -G /dev/sdb
```

## Extract partition from block device

First list the contents of the drive:
```
# sfdisk -l -uS image-file
Disk mwex-sda: cannot get geometry

Disk mwex-sda: 17750 cylinders, 255 heads, 63 sectors/track
Warning: extended partition does not start at a cylinder boundary.
DOS and Linux will interpret the contents differently.
Units = sectors of 512 bytes, counting from 0

   Device Boot    Start       End   #sectors  Id  System
   mwex-sda1   *      2048    206847     204800  83  Linux
   mwex-sda2        206848  50538495   50331648  83  Linux
   mwex-sda3      50538496  67315711   16777216  82  Linux swap / Solaris
   mwex-sda4      67315712 285155327  217839616   5  Extended
   mwex-sda5      67317760 285155327  217837568  83  Linux
```

Now, suppose you want to extract partition number 2. You can see that it
starts at block `4267680` and is `137985600` blocks long. This translates
into:

```
dd if=image-file of=partition3-file skip=206848 count=50331648
```

Peeking into the contents of the partition is as easy as:

```
# mount -o loop,ro partition3-file /mnt/hack
```
