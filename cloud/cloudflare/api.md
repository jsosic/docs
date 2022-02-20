# CloudFlare API

## Getting list of DNS entries

```
export CLOUDFLARE_ZONE_ID=0e59208fea0387ca6ae5498dde105c2f
export CLOUDFLARE_EMAIL=jsosic@example.com
export CLOUDFLARE_TOKEN=4fdf1a29c005aca9e6c6bec2c45cc36a
curl -X GET "https://api.cloudflare.com/client/v4/zones/${CLOUDFLARE_ZONE_ID}/dns_records" \
     -H "X-Auth-Email: $CLOUDFLARE_EMAIL" \
     -H "X-Auth-Key: $CLOUDFLARE_TOKEN" \
     -H "Content-Type: application/json" | jq
```

## Clear cache

```
#!/usr/bin/env bash

echo "Purge the cloudflare clache for sagemath.com -- see https://api.cloudflare.com/#zone-purge-all-files"

set -e

export API_KEY=$HOME/secrets/cloudflare/cloudflare

if [ ! -f $API_KEY ]; then
  echo "$0: You must put the CloudFlare API key in '$API_KEY'."
  exit 1
else
  echo "$0: Contacting CloudFlare servers to clear cache."

  curl -X DELETE "https://api.cloudflare.com/client/v4/zones/1f0851c75f9337545904475a1d1bbe71/purge_cache" \
     -H "X-Auth-Email: office@sagemath.com" \
     -H "X-Auth-Key: `cat $API_KEY`" \
     -H "Content-Type: application/json" \
     --data '{"purge_everything":true}'
fi
```
