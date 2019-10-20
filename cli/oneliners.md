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
