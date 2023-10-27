# Packemaker CLI hints

## Debugging

Cluster status:

```
pcs status
```

Show resource config

```
pcs resource config <resource_name>
```

Start and stop resource

```
pcs resource enable <resource_name>
pcs resource disable <resource_name>
```

If the resource is part of clone set, and you want to disable it only on one node:

```
pcs resource ban --wait <resource_name> <node_name>
```

To clear the constraints (move, ban, etc..):

```
pcs resource clear <resource_name>
```

## Stonith

Test fencing:

```
pcs stonith fence <node_name>
```

To clean fence/stonith failed history, run:

```
pcs stonith history cleanup
```

Disable fencing temporary

```
pcs property set stonith-enabled=false
```

```
pcs resource delete VirtualIP
```

## Maintenance

Put whole cluster to maintenance:

```
pcs property set maintenance-mode=true
```

Put single node into maintenance:

```
pcs node maintenance --node <node_name>
```

Bring back all nodes from maintenance:

```
pcs node unmaintenance --all
```
