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
