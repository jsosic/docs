# SystemD

## Generate a graph of boot process

```
systemd-analyze plot > boot.svg
```

## Clean journald logs

Delete older then N days:

```
journalctl --vacuum-time=2d
```

Delete everything older then the last N bytes:

```
journalctl --vacuum-size=500M
```

## System limits

The mappings of systemd limits to ulimit

```
Directive        ulimit equivalent     Unit
LimitCPU=        ulimit -t             Seconds      
LimitFSIZE=      ulimit -f             Bytes
LimitDATA=       ulimit -d             Bytes
LimitSTACK=      ulimit -s             Bytes
LimitCORE=       ulimit -c             Bytes
LimitRSS=        ulimit -m             Bytes
LimitNOFILE=     ulimit -n             Number of File Descriptors 
LimitAS=         ulimit -v             Bytes
LimitNPROC=      ulimit -u             Number of Processes 
LimitMEMLOCK=    ulimit -l             Bytes
LimitLOCKS=      ulimit -x             Number of Locks 
LimitSIGPENDING= ulimit -i             Number of Queued Signals 
LimitMSGQUEUE=   ulimit -q             Bytes
LimitNICE=       ulimit -e             Nice Level 
LimitRTPRIO=     ulimit -r             Realtime Priority  
LimitRTTIME=     No equivalent
```

If a ulimit is set to `unlimited` set it to `infinity` in the systemd config:

- `ulimit -c unlimited` is the same as `LimitCORE=infinity`
- `ulimit -v unlimited` is the same as `LimitAS=infinity`
- `ulimit -m unlimited` is the same as `LimitRSS=infinity`
