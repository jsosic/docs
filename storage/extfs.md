# ExtFS

## Dump and restore through pipe

This can be used to copy filesystem content to another FS.

Presumtions:
* new block dev is `mkfs.extX`-ed and mounted at `/mnt/dest`
* old block dev is `/dev/sdb3` and is not mounted

```
dump -0af -  /dev/sdb3 | ( cd /mnt/dest ; restore -rf - )
```
