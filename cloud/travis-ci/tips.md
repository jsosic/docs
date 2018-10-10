# Travis CI tips

## Getting current branch name in PR

Variable `$TRAVIS_BRANCH` is not always giving a correct information.

TRAVIS_BRANCH:
- for push builds, or builds not triggered by a pull request, this is the name of the branch.
- for builds triggered by a pull request this is the name of the branch targeted by the pull request.
- for builds triggered by a tag, this is the same as the name of the tag (TRAVIS_TAG).

To circuvment this, following example from `.travis.yml` can be used:

```
after_success:
  - TRAVIS_CURRENT_BRANCH=${TRAVIS_PULL_REQUEST_BRANCH:-$TRAVIS_BRANCH}
```
