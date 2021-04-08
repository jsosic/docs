# MongoDB system

## Create admin user

```
db.createUser(
  {
     user: "admin",
     pwd: "password",
     "roles" : [
       {
         "role" : "root",
         "db" : "admin"
       },
       {
         "role" : "clusterAdmin",
         "db" : "admin"
       },
       {
         "role" : "userAdminAnyDatabase",
         "db" : "admin"
       }
     ],

  }
)

```

## Dump

```
mongodump --authenticationDatabase admin -u admin -p zNUeYsr6xD47MCMD3A6u --port 17017 --db db_name --gzip --archive=db_name.gz
```

## Restore

```
]# ^C
cat db_name.gz | mongorestore --drop --authenticationDatabase admin -u admin -p zNUeYsr6xD47MCMD3A6u --port 17017 --gzip --archive
```
