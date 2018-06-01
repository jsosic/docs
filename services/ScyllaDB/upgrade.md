# ScyllaDB upgrade

## Check cluster status

```
[root@alt-servicedb04 ~]# nodetool describecluster
Cluster Information:
	Name: servicedb
	Snitch: org.apache.cassandra.locator.SimpleSnitch
	Partitioner: org.apache.cassandra.dht.Murmur3Partitioner
	Schema versions:
		41f0a1b2-64a9-3346-91e0-72951ecdf0a1: [10.200.132.145, 10.200.132.142, 10.200.132.143, 10.200.132.144]


[root@alt-servicedb04 ~]# nodetool status
Datacenter: datacenter1
=======================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address         Load       Tokens       Owns    Host ID                               Rack
UN  10.200.132.142  2.29 TB    256          ?       d136dc73-1949-40a4-969a-3381d2d117d2  rack1
UN  10.200.132.143  2.25 TB    256          ?       c24bfbca-913c-4d99-9b0b-ee89bf772b10  rack1
UN  10.200.132.144  2.21 TB    256          ?       45aae1fa-e38b-4931-8618-786ba43f9d0d  rack1
UN  10.200.132.145  1.91 TB    256          ?       5d770925-3954-4052-adb9-97bc1b5d8a2f  rack1

Note: Non-system keyspaces don't have the same replication settings, effective ownership information is meaningless
```

## Drain connections and create snapshot

```
nodetool drain
nodetool snapshot
```

## Stop and upgrade scylla

```
systemctl stop scylla-server
yum update scylla\*
systemctl start scylla-server
```

## Check local version

```
curl -X GET "http://localhost:10000/storage_service/scylla_release_version"
```
