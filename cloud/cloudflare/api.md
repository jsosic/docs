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
