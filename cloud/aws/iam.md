# AWS IAM

To generate a new keys run:

```
aws iam create-access-key --user-name <username>
```

To list access keys:

```
aws iam list-access-keys --user-name <username>
```

To drop an access key:

```
aws iam delete-access-key --user-name <username> --access-key-id <access_key_id>
```
