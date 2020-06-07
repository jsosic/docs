# PAM

## Lastlog issue on EL6

Problem:

```
su - user does not update lastlog database, as reported by lastlog -u or
finger . Both the commands show Never logged in
```

Resolution:

For updating the lastlog database, add below lines to beginning of the session
part of `/etc/pam.d/system-auth` and `/etc/pam.d/password-auth` files. Rest
configuration remains the same.

```
session     [success=1 default=ignore] pam_succeed_if.so service !~ gdm* service !~ su* quiet
session     optional    pam_lastlog.so nowtmp showfailed
```
