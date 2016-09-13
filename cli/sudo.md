# Sudo

## Sudoers

### Regexes

Example of how to use regex to negate some commands:
- allow `chown` of `/home/*`
- stop smartass user from `chown /home/bla/../../`
- stop smartass user from `chown user /home/bla /path2`

```
/bin/chown [a-z]*[0-9] /home/[a-z]*[0-9]/* ,\
! /bin/chown [a-z]*[0-9]     *..* ,         \
! /bin/chown * * *-[A-Za-z]*                \
```
