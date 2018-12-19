# SELinux

## AVCs not present in audit log

There is a dontaudit rule that is blocking messages in audit log.
To disable a dontaudit rule, run:

```
# semodule -DB
```

To turn it back on, run:

```
# semodule -B
```

## Creating modules

Extract offending log lines from `/var/log/audit/audit.log`,
matching string `avc`, and create a module:

```
cat /var/log/audit/audit.log | audit2allow -m mymodule
```

Example output:

```
module mymodule 1.0;

require {
  type httpd_t;
  type httpd_sys_content_t;
  type initrc_t;
  class sock_file write;
  class unix_stream_socket connectto;
}

#============= httpd_t ==============
allow httpd_t httpd_sys_content_t:sock_file write;
allow httpd_t initrc_t:unix_stream_socket connectto;
```

Save output to a file `mymodule.te`, and create a loadable
SELinux module from it:

```
checkmodule -M -m -o mymodule.mod mymodule.te
semodule_package -o mymodule.pp -m mymodule.mod
```

Finally, load a module:

```
semodule -i mymodule.pp
```

To remove policy package file, run:

```
semodule -r mymodule
```
