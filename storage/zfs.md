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


