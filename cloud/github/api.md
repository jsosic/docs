# GitHub API

To generate a new API token, go to [GitHub token page](https://github.com/settings/tokens).

## curl

### List all repositories

```
$ curl -s -H "Authorization: token <token>" 'https://api.github.com/orgs/lutak-srce/repos'
```

If you only want full names, you can pipe the output to `jq`:

```
| jq '.[].full_name'
```

### Pagination

GitHub prints 30 results by default. You can increase pagination with `per_page` option,
and select a custom page with `page` param:

```
$ curl -s -H "Authorization: token <token>" 'https://api.github.com/orgs/lutak-srce/repos?per_page=100&page=2'
```
