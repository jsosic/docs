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

### Running queries

```
SELECT usename,client_addr,waiting,query_start,state,pid FROM pg_stat_activity;
SELECT datname,usename,procpid,client_addr,waiting,query_start,current_query FROM pg_stat_activity;
```

Top-like view of current queries, grouped by how many of the same query are
running at that instant and the usernames belonging to each connection:

```
SELECT COUNT(*) as cnt, usename, current_query FROM pg_stat_activity GROUP BY usename,current_query ORDER BY cnt DESC;
```

### Database sizes

```
SELECT pg_size_pretty(pg_database_size('mydatabasename')) As fulldbsize;
```

### Table sizes

```
SELECT table_schema, table_name, pg_relation_size('"'||table_schema||'"."'||table_name||'"')
FROM information_schema.tables ORDER BY 3;
```

### Table sizes (table, index, total)

```
SELECT
    table_name,
    pg_size_pretty(table_size) AS table_size,
    pg_size_pretty(indexes_size) AS indexes_size,
    pg_size_pretty(total_size) AS total_size
FROM (
    SELECT
        table_name,
        pg_table_size(table_name) AS table_size,
        pg_indexes_size(table_name) AS indexes_size,
        pg_total_relation_size(table_name) AS total_size
    FROM (
        SELECT ('"' || table_schema || '"."' || table_name || '"') AS table_name
        FROM information_schema.tables
    ) AS all_tables
    ORDER BY table_size DESC
) AS pretty_sizes;
```
