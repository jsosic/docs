# Elasticsearch cluster

## General

Check cluster health:

```
curl -k -XGET 'https://127.0.0.1:9200/_cluster/health?pretty' -H "Authorization: Basic $(echo -n 'username:password' | base64)"
```

## Nodes

Remove a node:

```
curl -XPUT "http://127.0.0.1:9200/_cluster/settings" -d  '{ "transient" :{ "cluster.routing.allocation.exclude._ip" : "X.X.X.X" } }'
```

Force a synced flush of a node:

```
curl -XPOST 'http://localhost:9200/_flush/synced'
```

## HTTPS and Authorization

Enable https by setting up XPack:

```
# Enabling xpack for security
xpack.security.enabled: true

xpack.security.transport.ssl.enabled: true
xpack.security.transport.ssl.verification_mode: certificate
xpack.security.transport.ssl.key: /etc/elasticsearch/certs/example.com.key
xpack.security.transport.ssl.certificate: /etc/elasticsearch/certs/example.com.crt

xpack.security.http.ssl.enabled: true
xpack.security.http.ssl.verification_mode: certificate
xpack.security.http.ssl.key: /etc/elasticsearch/certs/example.com.key
xpack.security.http.ssl.certificate: /etc/elasticsearch/certs/example.com.crt
```

Set up initial password:

```
/usr/share/elasticsearch/bin/elasticsearch-setup-passwords interactive
```
