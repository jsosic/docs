# Redis CLI

## Drop all keys with certain prefix

```
EVAL "for _,k in ipairs(redis.call('keys','my_key_prefix*')) do redis.call('del',k) end" 0
```
