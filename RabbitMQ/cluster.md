# RabbitMQ Cluster

## Upgrade procedure

### Minor version upgrade

Minor versions can be upgraded one node at a time.

### Major version and ErLang upgrades

Whole cluster has to be stopped for the upgrade.

Proposed HA for this upgrade (if possible):
- spin up temporary instance or cluster
- move producers to temporary cluster
- let consumers empty all queues from original cluster
- move consumers to temporary cluster
- *upgrade* original cluster
- move producers back to original cluster
- let consumers empty all queues from temporary cluster
- move consumers to original cluster

Note: start disk nodes first. Any RAM node will emit errors if there are
no disk nodes present in the cluster.

## Firewall

Unless configured otherwise, following ports need to be open among cluster
members:
- TCP/4369
- TCP/25672
