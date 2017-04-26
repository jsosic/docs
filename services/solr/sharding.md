# Sharding solr

The process to shard the the single core into collection with multiple shards is:

1. Create a new Solr cloud

2. Create a core that has 1 SHARD and 1 REPLICA (eg: `test`)

3. Upload core's config to ZooKeeper

4. Stop the source node (where the original core lives) and stop newly created solr cloud

5. Copy the data/ directory from original Solr node to `test` core

6. Start solr cloud (and start original node if needed)

7. Shard the collection `test` by running the URI: '/admin/collections?action=CREATESHARD'

This will create the following cores:
`test_shard1_replica1`
`test_shard1_replica2`
`test_shard2_replica1`
`test_shard2_replica2`
on 4 different nodes.

8. After the sharding is done, drop the `test` collection.

9. Create a new collection named `test` which will have 2 shards and 2 replicas.

10. Stop all the solr nodes.

11. Stop the cloud and copy over the `test_shard1_replica1` to corresponding shard in
    newly created collection, and also copy the `test_shard1_replica2` to others.
    You can see the shards in solr admin cloud gui.

12. Start all the solr nodes and drop the old collection if everything works and document count matches

*Note*: steps 8 - 12 are there just to create a regularly named collection. If you don't have OCD you can stop at
step 8.
