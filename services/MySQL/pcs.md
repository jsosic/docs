# MySQL with Pacemaker

## Refresh slave

If we have the state similar to the following:

```
Full List of Resources:
  * Clone Set: p_mysql-clone [p_mysql] (promotable):
    * Promoted: [ db01.intern.lan ]
    * Unpromoted: [ db02.intern.lan ]
```

and want to refresh unpromoted node (maybe servers is out of sync from master),
we need to to do the following:
- put node `db02.intern.lan` into maintenance
- shut down MySQL on `db02.intern.lan`
- delete data from MySQL data dir
- create binary backup on `db01.intern.lan`
- transfer binary backup data to MySQL datadir on `db02.intern.lan`
- start MySQL outside of PCS cluster
- set up replication
- wait for the replication to catch up
- stop replication
- record current position on slave from `SHOW REPLICA STATUS\G`
- stop mysql
- update cluster `cib.xml` with replication info
- bring node `db02.intern.lan` back from maintenance

### Node 2 maintenance

```
pcs node maintenance db02.intern.lan
pkill mysqld
rm -rfv /var/lib/mysql/*
```

### Node 1 binary backup

```
sshfs root@db02.adriatic.local:/var/lib/mysql /mnt/backup
xtrabackup --defaults-file=/etc/my.cnf.d/server.cnf -u root --password=<password> -S /var/lib/mysql/mysql.sock --backup --open-files-limit 4096 --target-dir=/mnt/backup/
xtrabackup --defaults-file=/etc/my.cnf.d/server.cnf -u root --password=<password> -S /var/lib/mysql/mysql.sock --prepare --open-files-limit 4096 --target-dir=/mnt/backup/
umount /mnt/backup
```


### Node 2 recovery

```
/usr/bin/chown -R mysql: /var/lib/mysql
/usr/sbin/restorecon -R /var/lib/mysql
systemctl unmask mysqld
systemctl start mysqld
echo "CHANGE MASTER TO                \
MASTER_HOST='<ip_of_db01>',           \
MASTER_USER='<replication_user>',     \
MASTER_PASSWORD='<replication_pass>', \
MASTER_LOG_FILE='$(grep binlog_pos  /var/lib/mysql/xtrabackup_info  | cut -d\' -f 2)', \
MASTER_LOG_POS=$(grep binlog_pos /var/lib/mysql/xtrabackup_info  | cut -d\' -f 4); " | /usr/bin/mysql
echo "START SLAVE; " | /usr/bin/mysql
```

Now wait for the replica to fully catch up, and record the output of:

```
echo "SHOW SLAVE STATUS\G" | mysql
```

After that, we can stop mysql:

```
systemctl stop mysqld
systemctl mask mysqld
```

### Update cluster CIB file

Before we bring the node back from maintenance, we need to update the cluster cib file.

```
pcs cluster cib temp-cib.xml
```

Find a line that says something like:

```
<nvpair id="mysql_replication-p_mysql_REPL_INFO" name="p_mysql_REPL_INFO" value="172.168.70.203|mysql-bin.194288|30663290"/>
```
and update the IP, binlog filename and position. Save the file and run:

```
pcs cluster cib-push temp-cib.xml
```

Finally bring the node back from dead:

```
pcs node unmaintenance db02.intern.lan
```
