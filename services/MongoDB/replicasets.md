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
