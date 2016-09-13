# Git

## Edit commits

Edit last commit (without and with editing author):

```
git commit --amend
git commit --amend --author="Jakov Sosic <jsosic@gmail.com>"
```

## Export and import patches

Export:

```
git format-patch -<n> <SHA1> --stdout > /tmp/example.patch
```

Import:

```
git am /tmp/example.patch
```

Example (export last 10 commits):

```
git format-patch -10 HEAD --stdout > /tmp/0001-last-10-commits.patch
git am /tmp/0001-Commit-Message-of-the-Commit.patch
```
