# MySQL system

## System commands

### Triggers

List all triggers:

```
SELECT trigger_schema, trigger_name FROM information_schema.triggers;
```

### Tables without auto-increment

Find tables without auto-increment primary key:

```
SELECT t.table_schema, t.table_name 
  FROM information_schema.tables t 
    WHERE NOT EXISTS 
      (SELECT * FROM information_schema.columns c
       WHERE t.table_schema = c.table_schema  
         AND t.table_name = c.table_name
         AND c.column_key = 'PRI'
         AND c.extra = 'auto_increment')
```
