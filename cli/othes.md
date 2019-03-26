# Others

## Seq

Format output with zeroes when using `seq`:

```
seq --format='%02g' 1 20
```

## Java

Check default values of Initial and Max Heap (displayed in bytes):

```
java -XX:+PrintFlagsFinal -version | egrep '(Initial|Max)HeapSize'
```
