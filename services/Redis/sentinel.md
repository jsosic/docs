# Redis Sentinel

Use `redis-cli` with port 26379 to connect to sentinel.

Note: you always want to tail the `/var/log/sentinel/sentinel.log`
on all sentinels to see the cluster interaction.

```
redis-cli -p 26379
```

List masters for all managed clusters:

```
127.0.0.1:26379> sentinel masters                               # to get all masters (or if you don't know the cluster name)
127.0.0.1:26379> sentinel master <cluster-id>                   # get current sentinel master
127.0.0.1:26379> sentinel get-master-addr-by-name <cluster-id>  # get current sentinel master IP
```

Determine slaves for one cluster

```
sentinel slaves <cluster-id>
```

Force failover

```
sentinel failover <cluster-id>
```
