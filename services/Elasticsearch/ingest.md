# Elasticsearch ingest pipelines

## List all ingest pipelines

```
curl -s -X GET 'http://127.0.0.1:9200/_ingest/pipeline?pretty' | jq '.|keys'
```

## Show single ingest pipeline

```
curl -s -X GET 'http://127.0.0.1:9200/_ingest/pipeline/filebeat-7.2.0-mymodule-mysub-pipeline?pretty' | jq
```


## Delete ingest pipeline

```
curl -s -XDELETE 'http://127.0.0.1:9200/_ingest/pipeline/filebeat-7.2.0-mymodule-mysub-pipeline?pretty'
```
