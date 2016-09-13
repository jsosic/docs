# MongoDB Queries

## Slow queries

Print queries running longer then 5 seconds (without replication threads):

```
db.currentOp( { desc: { $not: /^repl writer worker/ } } ).inprog.forEach(function(op) {if(op.secs_running > 5) printjson(op.opid);})
```

Kill queries running longer then 5 seconds (exclude replication threads):

```
db.currentOp( { desc: { $not: /^repl writer worker/ } } ).inprog.forEach(function(op) {if(op.secs_running > 5) db.killOp(op.opid);})
```
