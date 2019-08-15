# HaProxy Socket

One can control HaProxy through socket. To enable admin level socket,
set the following line in `haproxy.cfg`:

```
global
    stats socket /var/lib/haproxy/stats level admin
```

After that, use `hatop` to connect to socket.

## List all available backend/nodes

```
for i in $(echo "show stat" | socat /var/lib/haproxy/stats stdio | egrep -v '^#|^$|mysql|(FRONT|BACK)END' | cut -d, -f1,2 | sed 's/,/\//'); do echo $i; done
```

## Gracefuly removing a node

To gracefuly remove a node from a backend, run `hatop`:

```
hatop -s /var/lib/haproxy/stats
```

After you get `hatop` ncurses GUI, press <tab> to go to tab #5 (CLI).

Enter the following command:

```
disable server <backend_name>/<server_name>
```
