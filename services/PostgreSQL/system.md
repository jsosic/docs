# PostgreSQL system

## System commands

### Dump and restore

Dump and restore optimized for speed:
```
pg_dump -Z2 -Fc db_name -f /tmp/db_name.gz
pg_restore -j 8 -d db_name /tmp/db_name.gz
```

If we want to restore with different owner:

```
pg_restore -j 8 --no-owner --role=user2 -d db_name /tmp/db_name.gz
```

Dump schema only:

```
pg_dump -s -d db_name -n schema_name
```

### Copy roles between servers

Dump and restore roles (without passwords):
```
pg_dumpall -g > roles.sql
psql -U postgres -f roles.sql
```

### Create md5 hash for user password

```
$ python
>>> import hashlib
>>> password='pero'
>>> username='pero'
>>> "md5" + hashlib.md5(password + username).hexdigest()
'md50008c0dab2ff4c2f7e84fcab7eb8db6a'
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

## System upgrade

Install new binaries, initdb, stop both servers, and run:

```
/usr/pgsql-10/bin/pg_upgrade -b /usr/pgsql-9.3/bin -B /usr/pgsql-10/bin -d /var/lib/pgsql/9.3/data -D /var/lib/pgsql/10/data
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

## Database repair

### Amcheck

Amcheck is utility for checking database for corruption.

```
# yum install amcheck_next11 --enablerepo=pgdg11
psql> CREATE EXTENSION amcheck_next;
```

After install, run check:

```
SELECT bt_index_check(index => c.oid, heapallindexed => i.indisunique),
        c.relname,
        c.relpages
FROM pg_index i
JOIN pg_opclass op ON i.indclass[0] = op.oid
JOIN pg_am am ON op.opcmethod = am.oid
JOIN pg_class c ON i.indexrelid = c.oid
JOIN pg_namespace n ON c.relnamespace = n.oid
WHERE am.amname = 'btree' AND n.nspname = 'pg_catalog'
-- Don't check temp tables, which may be from another session:
AND c.relpersistence != 't'
-- Function may throw an error when this is omitted:
AND c.relkind = 'i' AND i.indisready AND i.indisvalid
ORDER BY c.relpages DESC LIMIT 10;
```
