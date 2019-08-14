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
openssl req -text -noout -verify -in certificate.csr.pem
```

# Check a private key

```
openssl rsa -in certificate.key.pem -check
```

# Check if key and cert match

```
openssl x509 -noout -modulus -in certificate.crt.pem | openssl md5
openssl  rsa -noout -modulus -in certificate.key.pem | openssl md5
openssl  req -noout -modulus -in certificate.csr.pem | openssl md5
```

# Check a PKCS#12 keystore

```
openssl pkcs12 -info -in keyStore.p12
```

# Check a remote SSL certificate, and print all certificates (including intermediates)

```
openssl s_client -connect <remote_server>:443
```

# Download a certificate

```
export domain_name=example.com
echo -n | openssl s_client -connect ${domain_name}:443 | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > ${domain_name}.crt.pem
```

# Print CRL info

```
openssl crl -text -noout -in some.crl
```
