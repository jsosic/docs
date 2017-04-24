# Grep

## Highlight non-ASCII characters

Sometimes when you get `An invalid XML character (Unicode: 0x1b)` or something similar,
there is a need to find an non-ASCII characters. Grep can help:

```
grep --color='auto' -P -n "[^\x00-\x7F]" /path/to/foo.xml
```
