# PostgreSQL replication

## RO commands

### Check replication status

```
select client_addr, state, sent_location, write_location,
        flush_location, replay_location from pg_stat_replication;
```

### Check replication lag (in seconds)

```
select now()-pg_last_xact_replay_timestamp();
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
