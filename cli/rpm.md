# Rpm

## Rebuild binary rpm

To modify spec file of an existing RPM when you don't have the spec file,
use `rpmrebuild`:

```
$ rpmrebuild -enp package-[version]-[release]-el7.[arch].rpm
```

## Yum Shell

How to remove some packages and install other through `yum shell`:

```
# yum shell
Setting up Yum Shell
> remove mysql56u mysql56u-server
> install Percona-Server-server-57 Percona-Server-client-57
> run
```

Example:

```
# cat <<EOF > /tmp/transactions.txt
remove mysql56u mysql56u-server
install Percona-Server-server-57 Percona-Server-client-57
run
EOF

# yum shell /tmp/transactions
```


## Yum swap packages

```
yum swap -- install foo -- remove bar
```

## Yum old repositories

* [RpmForge sources](http://repoforge.gtdinternet.com/source/)
