# Yum

## Shell

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


## Swap packages

```
yum swap -- install foo -- remove bar
```

## Old repositories

* [RpmForge sources](http://repoforge.gtdinternet.com/source/)
