# PostgreSQL system

## System commands

### Dump and restore

Dump and restore optimized for speed:
```
pg_dump -Z2 -Fc db_name -f /tmp/db_name.gz
pg_restore -j 8 -d db_name /tmp/db_name.gz
```

### Copy roles between servers

Dump and restore roles (without passwords):
```
pg_dumpall -g > roles.sql
psql -U postgres -f roles.sql
```

### Views

Show views on a particular table:

```
SELECT table_name from INFORMATION_SCHEMA.views;
```

Without system views:

```
SELECT table_name from INFORMATION_SCHEMA.views WHERE table_schema = ANY (current_schemas(false));
```

## System queries

Show currently running queries:

```
SELECT usename,client_addr,waiting,query_start,state,pid FROM pg_stat_activity;
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
