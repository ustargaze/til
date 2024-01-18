# filter-branch

> 参考：https://git-scm.com/docs/git-filter-branch

git commit --amend 只能修改上一条 commit 的信息。

如果需要大量重写 git 历史记录，可以通过 filter-branch 实现。

例如：将 git 记录中的用户名和邮箱修改为同一个，可以使用以下命令

```sh
git filter-branch --commit-filter '
    GIT_AUTHOR_NAME="name";
    GIT_AUTHOR_EMAIL="name@example.com";
    git commit-tree "$@"' HEAD
```

使用 filter-branch 之后需要强制推送到远程操作。

<font color=red>**慎用！！！**</font>