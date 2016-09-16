# PostgreSQL system

## System commands

### Dump and restore

Dump and restore optimized for speed:
```
pg_dump -Z2 -Fc db_name -f /tmp/db_name.gz
pg_restore -j 8 -d db_name /tmp/db_name.gz
```

## System queries

Show currently running queries:

```
SELECT datname,usename,procpid,client_addr,waiting,query_start,current_query FROM pg_stat_activity;
```

Top-like view of current queries, grouped by how many of the same query are
running at that instant and the usernames belonging to each connection:

```
SELECT COUNT(*) as cnt, usename, current_query FROM pg_stat_activity GROUP BY usename,current_query ORDER BY cnt DESC;
```

Find out the size of the database:

```
SELECT pg_size_pretty(pg_database_size('mydatabasename')) As fulldbsize;
```
