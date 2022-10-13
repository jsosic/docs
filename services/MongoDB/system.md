# MongoDB system

## Dump

```
mongodump --authenticationDatabase admin -u admin -p zNUeYsr6xD47MCMD3A6u --port 17017 --db db_name --gzip --archive=db_name.gz
```

## Restore

```
]# ^C
cat db_name.gz | mongorestore --drop --authenticationDatabase admin -u admin -p zNUeYsr6xD47MCMD3A6u --port 17017 --gzip --archive
```

## Dump pipe to restore

```
mongodump --host <source_host>:<port> --db <database_name> --gzip --archive | \
mongorestore --drop -vvvvvv -h <target_host>:<port> --db <database_name> --gzip --archive```

## Compact collections

Compact (to recover unused disk space) all collections in a single database:

```
db.getCollectionNames().forEach(function (collectionName) {
    print('Compacting: ' + collectionName);
    db.runCommand({ compact: collectionName });
});
```
