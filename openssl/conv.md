# OpenSSL - convert certificates

## Convert a DER file (.crt .cer .der) to PEM

```
openssl x509 -inform der -in certificate.cer -out certificate.pem
```

## Convert a PEM file to DER

```
openssl x509 -outform der -in certificate.pem -out certificate.der
```

## Convert a PKCS#12 file (.pfx .p12) containing a private key and certificates to PEM

You can add `-nocerts` to only output the private key or add -nokeys to only output the certificates.

```
openssl pkcs12 -in keyStore.pfx -out keyStore.pem -nodes
```

## Convert a PEM certificate file and a private key to PKCS#12 (.pfx .p12)

```
openssl pkcs12 -export -out certificate.pfx -inkey privateKey.key -in certificate.crt -certfile CACert.crt
```

## Convert private key from PKCS#1 format to PKCS#8

```
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in pkcs1.key -out pkcs8.key
```
