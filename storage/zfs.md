# ZFS

## Snapshots

Calculate snapshots size:

```
zfs get -Hp used | grep 2014 | awk -F' ' 'BEGIN { total=0 } { $total+=$3 } END { print $total/1024/1024; }'
```

### Send-Recv backups

From local pool to local pool:

```
zfs send tank/public@snap1 | zfs recv backuptank/public
```

To remote pool over ssh:

```
zfs send tank/public@snap1 | ssh user@remotehost zfs recv tank/public
```

Incrementals to remote pool over ssh:

```
zfs send -i tank/public@snap1 tank/public@snap2 | ssh user@remotehost zfs recv tank/public
zfs send -i tank/public@snap2 tank/public@snap3 | ssh user@remotehost zfs recv tank/public
zfs send -i tank/public@snap3 tank/public@snap4 | ssh user@remotehost zfs recv tank/public
```

## Errors

### Dataset does not exist

Determine clone names:

```
zdb -d <poolname> | grep %
```

Destroy identified clones:

```
zfs destroy <clone-with-%-in-the-name>
```

Command may complain that `dataset does not exist`, and you should check
again if there are more clones. 

## ACLs on Solaris

Check existing ACLs:

```
ls -v
```

Adding new ACLs (allow everything to group `domain admins`):

```
chmod -R  A+group:domain\ admins:read_data/write_data/execute:allow jakov.sosic
chmod -R  A+group:domain\ admins:list_directory/read_data/add_file/write_data/add_subdirectory/append_data/write_xattr/execute/write_attributes/write_acl/write_owner/delete/delete_child:allow
```


## Tunning

### Basics

Check logical block size on vdevs:

```
smartctl -a /dev/sdX | grep -i block
```

If there is a NVME, command is:

```
nvme list
```

Depending on `512b` vs `4k`, create zpool with either `ashift=9` or `ashift=12`:

```
zpool create -f -O compression=lz4 -O atime=off -o ashift=9 <pool_name> <list_of_vdevs>
```

**Note**: you can find out block vdev sector size like [this](https://gitlab.com/jsosic/docs/blob/master/storage/hardware.md#block-device-sector-size).

Some settings (except for `ashift`) can be added later:

```
zfs set compression=lz4 xattr=sa atime=off tank
```

* compression is good if we have modern CPU which is mostly idle
* `xattr=sa` stores attributes to system attributes instead of storing them to a file in a hidden dir (more seeks while reading)

More [info](https://www.svennd.be/basic-zfs-tune-tips/).

### Resilver speed

Lower the number of ticks to delay prior to issuing a resilver I/O:

```
echo 0 > /sys/module/zfs/parameters/zfs_resilver_delay
```

Lower the idle time (number of clock ticks) by which the scan operation is delayed
if the user I/O has been detected:

```
echo 0 > /sys/module/zfs/parameters/zfs_scan_idle
```

More info:
* [Tunning of ZFS module](https://www.svennd.be/tuning-of-zfds-module/) by svennd

## Resizing

### Expand single vdev

Vdevs can be expanded only by replascing them. So in a virtual environment,
one can do the following to expand zpool vdev:

```
zpool set autoexpand=on tank
zpool replace tank /dev/sdb /dev/sdc
wipefs -af /dev/sdb
zpool replace tank /dev/sdc /dev/sdb
```
