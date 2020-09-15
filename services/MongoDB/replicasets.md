# MongoDB Replica Sets

## Check replica lag

```
rs.printSlaveReplicationInfo()
```

## Hide a slave node

```
var cfg = rs.config();
var member = cfg.members[<node number>];
printjson(member)
member.priority = 1;
member.hidden = false;
member.votes = 1;
rs.reconfig(cfg)
```

## Add a new node (nonvoting, hidden)

```
rs.add( { host: "mongodb3.example.net:27017", priority: 0, votes: 0, hidden: true } )
```

## Create admin user

```
db.getSiblingDB("admin").createUser(
  {
    "user" : "admin",
    "pwd" : "clear_text_PW",
    roles: [ { "role" : "clusterAdmin", "db" : "admin" },
             { role: "userAdminAnyDatabase", db: "admin" } ]
  }
)
```
