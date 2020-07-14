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
