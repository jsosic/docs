# JVM memory

## Generate Heap Dump

```
jmap -J-d64 -dump:format=b,file=<path to dump file>  <jvm pid>
```

## Generate Heap Dump on OOM

Add following params to JVM:

```
-XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=<path to dump file>
```

## Analyse Heap Dump

Heap dump can be analyzed with `jhat` or `Eclipse Memory Analyzer`.
