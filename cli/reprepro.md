# Reprepro

## Creating a repo

Install `reprepro`:

```
apt-get install reprepro
```

Create dirs:

```
mkdir -p /var/www/reprepro/ubuntu/{conf,dists,incoming,indices,logs,pool,project,tmp}
cd /var/www/reprepro/ubuntu/
```

Create `distributions` file:

```
cat >> /var/www/reprepro/ubuntu/conf/distributions << EOF
Origin: Your Name
Label: Your repository name
Codename: karmic
Architectures: i386 amd64 source
Components: main
Description: Description of repository you are creating
SignWith: YOUR-KEY-ID
EOF
```

Create `options`:

```
cat >> /var/www/reprepro/ubuntu/conf/options << EOF
ask-passphrase
basedir .
EOF
```

Finally add some debs!

```
reprepro includedeb xenial /path/to/package_1.0-1.deb
```
