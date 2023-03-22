# Elasticsearch indices

## List all indices

```
curl localhost:9200/_cat/indices?v
curl -sS -X GET "localhost:9200/_cat/indices?v&pretty"
```

## Delete indice

```
curl -XDELETE "localhost:9200/index_name?pretty"
```

## Change settings

```
export i=some-index
curl -H "Content-Type: application/json" -XPUT "localhost:9200/${i}/_settings" -d '{"number_of_replicas": 0}' 
```

## Close and open index

```
export i=some-index
curl -XPOST "localhost:9200/${i}/_close"
curl -XPOST "localhost:9200/${i}/_open"
```

## Retry replica allocation

```
for i in $(curl -sS -X GET "localhost:9200/_cat/indices?v&pretty" | grep yellow | awk -F' ' '{ print $3 }'); do
  curl -sS -H 'Content-Type: application/json' \
       -d '{ "index": { "allocation": { "max_retries": 10 } } }' \
       -X PUT "localhost:9200/${i}/_settings";
done
```


## Move indice to another node in cluster

```
curl --user elastic:password -H "Content-Type: application/json" -XPOST "https://$(hostname -f):9200/_cluster/reroute" -d '
{
  "commands" : [
    {
      "move" : {
         "index" : "my-super-cool-index-2022.02.06-000070", "shard" : 0,
          "from_node" : "node01", "to_node" : "node02"
       }
    }
  ]
}'
```

## Show unassigned shards

### Print unassigned shards with a reason

```
curl --user elastic:password -XGET localhost:9200/_cat/shards?h=index,shard,prirep,state,unassigned.reason| grep UNASSIGNED
```

### Explain cluster allocation

```
curl --user elastic:password -XGET localhost:9200/_cluster/allocation/explain?pretty
```
