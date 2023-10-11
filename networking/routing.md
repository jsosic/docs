# Routing

## Source policy routing

When running multiple interfaces on Linux, asymetric routing can occur if packets are received via one interface,
but Linux doesnt have routes or networks to the source, so it sends replies via default gateway which is on another
interface.

To remedy this scenario, policy based routing can be used.

Sample scenario:

System has two interfaces:
- `eth0` - `10.41.0.11/16`, we want to contact whole 10.0.0.0/8 range through this interface
- `eth1` - `157.240.196.32/28`, public network with a default gateway

Setup will utilize two routing tables - one per each interface. Default system routing table will stay empty.
Whether the packet will be sent through `eth0` or `eth1` is decided by policies set up in ip rules.

### CentOS 7 and earlier versions

```
echo '111    60' >> /etc/iproute2/rt_tables
echo '112    20' >> /etc/iproute2/rt_tables
```

```
echo 'default via 10.41.0.1 table 60' >> /etc/sysconfig/network-scripts/route-eth0
echo 'priority 0 from 10.41.0.0/16 table 60' >> /etc/sysconfig/network/scripts/rule-eth0
echo 'priority 100 to 10.0.0.0/8 table 60' >> /etc/sysconfig/network/scripts/rule-eth0
```

```
echo 'default via 157.240.196.33 table 20' >> /etc/sysconfig/network-scripts/route-eth1
echo 'priority 200 to 157.240.196.32/28 table 20' >> /etc/sysconfig/network/scripts/rule-eth1
echo 'priority 300 to 0.0.0.0/0 table 20' >> /etc/sysconfig/network/scripts/rule-eth1
```


### CentOS 8+

RHEL v8 and higher have deprecated old `ifcfg-/rule-/route-` files and use `nmcli`.


```
nmcli conn modify "System eth0" ipv4.route-table 60
nmcli conn modify "System eth0" ipv4.routing-rules "priority 0 from 10.41.0.0/16 table 60"
nmcli conn modify "System eth0" +ipv4.routing-rules "priority 100 to 10.0.0.0/8 table 60"
```

```
nmcli conn modify 'System eth1' ipv4.addresses 157.240.196.35/28 ipv4.gateway 157.240.196.33
nmcli conn modify 'System eth1' ipv4.route-table 20
nmcli conn modify 'System eth1' ipv4.routing-rules "priority 200 from 157.240.196.32/28 table 20"
nmcli conn modify 'System eth1' +ipv4.routing-rules "priority 300 from 0.0.0.0/0 table 20"
```
