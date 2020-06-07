# Docker

## Release file descriptor of deleted files

```
docker_pid=$(cat /var/run/docker.pid)
gdb -p $docker_pid <<< "$( ls /proc/$docker_pid/fd -l --time-style=+'%s' | grep -E 'deleted' | awk '{ printf("p close(%s)\n", $7)}')" >/dev/null
```
