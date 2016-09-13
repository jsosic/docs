# Ansible

Gather facts from remote system:

```
ansible -i staging.ini -m setup my-fqdn.example.net
```

Run custom command on all hosts in inventory `staging.ini`:

```
ansible -i staging.ini -m command -a "uptime" all
```
