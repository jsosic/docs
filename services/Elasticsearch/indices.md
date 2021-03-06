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
