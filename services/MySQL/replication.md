# MySQL replication

## Percona InnoBackupEx

To set u replication, master needs to have binlog enabled.
Also, all servers need IDs set.

Set the following on master and (re)start mysql:

```
[mysqld]
log-bin=mysql-bin
server-id=1
```

Create the replication user on master:

```
mysql> CREATE USER 'replicator'@'172.168.70.%' IDENTIFIED BY '<secret>';
mysql> GRANT REPLICATION SLAVE ON *.* TO 'replicator'@'<slave_ip>';
```

Backup the master:

```
# innobackupex --defaults-file=/etc/my.cnf /tmp/backup
# innobackupex --defaults-file=/etc/my.cnf --apply-log /tmp/backup/2017-08-12_13-53-27/
```

Stop the slave, and delete everything from datadir.
Copy the backup from previous step to the datadir.

Set the following in slave `my.cnf`:


Also, set the following line temporary in slave config:

```
skip-slave-start
```

Start the MySQL on slave, and run the following:

```
# cd /var/lib/mysql
# echo "STOP SLAVE;" | mysql
# echo "CHANGE MASTER TO \
MASTER_HOST='10.10.10.10', \
MASTER_USER='replicator',     \
MASTER_PASSWORD='<secret>',   \
MASTER_LOG_FILE='$(cat xtrabackup_binlog_info | cut -s -f 1)', \
MASTER_LOG_POS=$(cat xtrabackup_binlog_info | cut -s -f 2); " | mysql
# echo "START SLAVE; " | mysql
```
