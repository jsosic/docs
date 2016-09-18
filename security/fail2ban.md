# Fail2ban

Remove locked out IP from a jail (`sshd` jail used in example):

```
fail2ban-client set sshd unbanip 208.117.229.247
```
