# CentOS

## Print basearch, releasever

### EL8 and newer

```
/usr/libexec/platform-python -c 'import dnf, json; db = dnf.dnf.Base(); print(json.dumps(db.conf.substitutions, indent=2))'
```

### EL7 and older

```
python -c 'import yum, pprint; yb = yum.YumBase(); pprint.pprint(yb.conf.yumvar, width=1)'
```

## Install newer dev tools (GCC & co)

```
# yum install centos-release-scl-rh
# yum install devtoolset-9
```

### Use newer dev tools

```
$ scl enable devtoolset-9 bash
```
