# Zookeeper ensemble

## Check which node is a leader

```
echo stat | nc localhost 2181 | grep Mode
echo srvr | nc localhost 2181 | grep Mode #(From 3.3.0 onwards)
```

## Add more nodes to Zookeeper ensemble

### Start 3-node ensemble and make sure everything is synced.

Example config:

```
tickTime=2000
dataDir=/var/lib/zookeeper
clientPort=2181
initLimit=50
syncLimit=200
server.1=zoo01:2881:3881
server.2=zoo02:2881:3881
server.3=zoo03:2881:3881
```

### Setup two new nodes to join ensemble

Example config:

```
tickTime=2000
dataDir=/home/vagrant/zook/conf4
clientPort=2181
initLimit=50
syncLimit=200
server.1=zoo01:2881:3881
server.2=zoo02:2881:3881
server.3=zoo03:2881:3881
server.4=zoo04:2881:3881
server.5=zoo05:2881:3881
```

Start the services on nodes 4 and 5.

### Update config of original nodes

Add the new nodes to config of the old nodes:

```
server.4=zoo04:2881:3881
server.5=zoo05:2881:3881
```

### Restart two followers

Two out of three old nodes will be followers and one will be leader.
Find the followers and restart them.

### Restart leader

After all 4 followers are running with new configuration and are in
sync you can safely restart leader.

### Create VHOST

Vhost can be created by using `solr` client binary, for example:

```
bin/solr zk mkroot /solr -z zoo:2181,zoo:2181,zoo:2181
```
