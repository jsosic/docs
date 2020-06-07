# Redis Sentinel

To connect to sentinel instances:

```
redis-cli -p 26379
```

List masters for all managed clusters:

```
127.0.0.1:26379> sentinel masters
```

List slaves for one cluster

```
sentinel slaves <cluster-name>
```
