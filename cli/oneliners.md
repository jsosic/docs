# Oneliners

## Delete all backups except from Sundays

If you have a filename in a `YYYYMMDD-<something>` format you can delete all the files
except (for example) Sunday:

```
for i in $(ls | sed 's/-.*//g'); do [[ $(date --date=$i +'%w') != 0 ]] && rm -fv ${i}*; done
```

## Delete multiple matcing lines from a file

```
sed -i -e ':a;N;$!ba;s/line1 match\nline2 match\n//' file
```

## Kill all processes of user

```
kill -TERM $(ps -u <username> -o "pid=")
```

## Find and remove dead symlinks

```
find /usr/bin -type l ! -xtype f ! -xtype d -ok rm -f {} \;
```


## Drop Linux caches

To free pagecache:

```
echo 1 > /proc/sys/vm/drop_caches
```

To free dentries and inodes:

```
echo 2 > /proc/sys/vm/drop_caches
```

To free pagecache, dentries and inodes:

```
echo 3 > /proc/sys/vm/drop_caches
```
