# OpenSSL - certificate information

## Check all certificate information

```
openssl x509 -in certificate.crt.pem -text
```

## Print only small headers: subject, startdate and enddate

```
openssl x509 -in certificate.crt.pem -text -subject -startdate -enddate
```

## Check a Certificate Signing Request (CSR)

```
openssl req -text -noout -verify -in CSR.csr
```

# Check a private key

```
openssl rsa -in certificate.key.pem -check
```

# Check a PKCS#12 keystore

```
openssl pkcs12 -info -in keyStore.p12
```

# Check a remote SSL certificate, and print all certificates (including intermediates)

```
openssl s_client -connect <remote_server>:443
```
