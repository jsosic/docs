# MongoDB auth

## Create admin user

```
db.createUser(
{
  user: "admin",
  pwd:  "password",
  "roles": [
    { "role": "root",                 "db": "admin" },
    { "role": "clusterAdmin",         "db": "admin" },
    { "role": "userAdminAnyDatabase", "db": "admin" }
  ],
}
)

```

## Create regular user

```
db.createUser(
{
  "user": "npc_user",
  "pwd":  "supersecret",
  "roles": [ { "role" : "dbOwner", "db" : "my_special_db" } ],
}
)
```

## List users

```
db.getUser("npc_user")
db.getUsers()
```

## Drop users

```
db.dropUser("npc_user")
```
