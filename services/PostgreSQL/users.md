# PostgreSQL users

## Check user privileges

To check tables owned by user, run:

```
SELECT * FROM pg_tables WHERE tableowner='username' ORDER BY tablename;

```

To check table grants, run:

```
SELECT * FROM information_schema.role_table_grants WHERE grantee='username' ORDER BY table_schema,table_name;
```

To check schemas with grants for specific user, run:

```
SELECT
  r.usename as grantor, e.usename as grantee, nspname, privilege_type, is_grantable
FROM pg_namespace, aclexplode(nspacl) AS a
  JOIN pg_user e ON a.grantee = e.usesysid
  JOIN pg_user r ON a.grantor = r.usesysid
WHERE e.usename = 'billing_readonly' ORDER BY nspname;
```
