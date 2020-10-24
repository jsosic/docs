# How to generate Solr Java trust/keystore

This file needs to be regenerated after every `example.com` (or other) certificate update.


## Generate empty keystore

Generate Java KeyStore with some temporary fake certificate,
and immediately remove the certificate to render the keystore
empty.

```
keytool -genkey -keyalg RSA -alias tempca -keystore example.com.keystore.ks
keytool -delete -alias tempca -keystore example.com.keystore.ks
```

## Convert private key and public certificate to PKCS12 format

Convert a PEM certificate file and a private key to PKCS#12 (.pfx .p12):

```
openssl pkcs12 -export -out tmp-cert.pfx -inkey example.com.key -in example.com.crt -certfile /etc/pki/ca-trust/extracted/openssl/ca-bundle.trust.crt
```

## Import cert with private key to empty JKS

```
keytool -v -importkeystore -srckeystore tmp-cert.pfx -srcstoretype PKCS12 -destkeystore example.com.keystore.ks -deststoretype JKS
rm -f tmp-cert.pfx
```
