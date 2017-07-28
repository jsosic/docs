# OpenSSL - generate certificate

## Generate new CSR (and private key)

```
cat >> certificate.cnf<<EOF
[req]
default_bits = 2048
prompt = no
default_md = sha256
distinguished_name = dn
 
[ dn ]
C=HR
ST=Split
L=Split
O=Heartbeat doo
OU=Infrastructure
emailAddress=jsosic@gmail.com
CN = heartbeat.hr
EOF

openssl req -new -newkey rsa:2048 -nodes -out certificate.csr.pem -keyout certificate.key.pem <( cat certificate.cnf )
```

## Generate a certificate signing request based on an existing certificate

```
openssl x509 -x509toreq -in certificate.crt.pem -out certificate.csr.pem -signkey certificate.key.pem
```

## Generate new CSR (for an existing private key)

```
openssl req -out certificate.csr.pem -key certificate.key.pem -new
```

## Generate self-signing certificate

```
openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout certificate.key.pem -out certificate.crt.pem
```

## Remove a passphrase from a private key

```
openssl rsa -in certificate.key.pem -out certificate-nopass.key.pem
```
