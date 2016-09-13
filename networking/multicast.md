# Multicast

Multicast is used as default communication mecahnism in RHCS v6.

## Check multicast connectivity

Generate mutlicast traffic:

```
nc -u -vvn -z <multicast_IP> 5405
```

Check if the other node can see packets:

```
tcpdump -i eth0 ether multicast
```
