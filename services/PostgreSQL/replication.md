# PostgreSQL replication

## RO commands

### Check if master or slave

```
SELECT pg_is_in_recovery();
```

### Check replication status

```
select client_addr, state, sent_location, write_location,
        flush_location, replay_location from pg_stat_replication;
```

### Check replication lag (in seconds)

```
select now()-pg_last_xact_replay_timestamp();
```

### Check replication slot status

```
SELECT slot_name,
       lpad((pg_control_checkpoint()).timeline_id::text, 8, '0') ||
       lpad(split_part(restart_lsn::text, '/', 1), 8, '0') ||
       lpad(substr(split_part(restart_lsn::text, '/', 2), 1, 2), 8, '0')
       AS wal_file
FROM pg_replication_slots;
```

### Change IP addresses of replication nodes in RepMGR

```
[root@db01/staging ~]# su - postgres

[postgres@db01/staging ~]$ psql repmgr
psql (9.6.5)
Type "help" for help.

repmgr=# SELECT * FROM repmgr.nodes;

repmgr=# UPDATE repmgr.nodes \
    SET conninfo = 'user=repmgr port=5432 sslmode=require sslcompression=1 krbsrvname=postgres password=12345678 host=10.30.91.201 application_name=db01.example.lan' \
    WHERE node_name='db01.example.lan';
```

## Streaming setup

### Configuring master `postgresql.conf`

Set `wal_level` to add information required to run read-only queries on a standby server:

```
wal_level = hot_standby
```

Wait for WAL records to be written to disk before the command returns a "success" to client

```
synchronous_commit = on
```

Send WAL segments to archive storage by setting `archive_command`:

```
archive_mode = on
archive_command = 'test ! -f /var/lib/pgsql/9.5/data/pg_archive/%f && cp %p /var/lib/pgsql/9.5/data/pg_archive/%f'
```

Terminate replication connections that are inactive longer than the specified number of milliseconds:

```
wal_sender_timeout = 6000s
```

Allow RO queries during recovery:

```
hot_standby = on
```

Send replies at least every X seconds:

```
wal_receiver_status_interval = 10s
```

### Create replication user on master

```
CREATE ROLE replication WITH REPLICATION PASSWORD 'replicassword' LOGIN;
```

### Restore slave from master backup

```
pg_basebackup -h 10.10.11.101 -D /var/lib/pgsql/9.5/data -U replication -v -P
```

### Configure slave

Set `recovery.conf`:

```
standby_mode = 'on'
primary_conninfo = 'host=10.10.11.101 port=5432 user=replication password=replicassword application_name=rhcs02'
restore_command = 'cp /var/lib/pgsql/9.5/data/pg_archive/%f %p'
recovery_target_timeline='latest'
```
