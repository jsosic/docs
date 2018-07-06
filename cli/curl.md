# Curl

## Fake hosts record on the fly

Instruct curl to connect to 127.0.0.1 instead of resolving `www.example.com`:

```
curl --resolve www.example.com:80:127.0.0.1 http://www.example.com/
```


## Measure timing of responses

Create a new file, `curl-format.txt`, and paste in:

```
   time_namelookup:  %{time_namelookup}\n
      time_connect:  %{time_connect}\n
   time_appconnect:  %{time_appconnect}\n
  time_pretransfer:  %{time_pretransfer}\n
     time_redirect:  %{time_redirect}\n
time_starttransfer:  %{time_starttransfer}\n
                   ----------\n
        time_total:  %{time_total}\n
```

Make a request:

```
curl -w "@curl-format.txt" -o /dev/null -s "http://wordpress.com/"
```
