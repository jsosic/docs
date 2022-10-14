# MongoDB system

## Dump

### Single database

```
mongodump --authenticationDatabase admin -u admin -p password --port 17017 --db db_name --gzip --archive=db_name.gz
```

### All databases

```
for db_name in $(echo 'show dbs' |  mongo --port 17017 | grep -v -E 'admin|config|local' | grep B$ | cut -d' ' -f 1); do
  mongodump --authenticationDatabase admin -u admin -p password --port 17017 --db ${db_name} --gzip --archive=${db_name}.gz
done

```

## Restore

### Single database

```
cat db_name.gz | mongorestore --drop --authenticationDatabase admin -u admin -p password --port 17017 --gzip --archive
```

### All databases

```
password=password
for db_name in $(echo * | sed 's/.gz//g'); do
  cat ${db_name}.gz | mongorestore --drop --authenticationDatabase admin -u admin -p ${password} --port 17017 --gzip --archive
done

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
