# Config Includes

> 参考：https://git-scm.com/docs/git-config/en

## 配置文件的存储路径及其作用范围

默认情况下，git config 会从下列多个文件中读取配置项：

- **`$(prefix)/etc/gitconfig`**

  系统范围的配置文件，对应作用范围为 **`system`**。

- **`$XDG_CONFIG_HOME/git/config`** 

  **`~/.gitconfig`**

  特定用户的配置文件，作用范围为 **`global`**。如果环境变量 `XDG_CONFIG_HOME`  没有设置或者为空是，默认为 `HOME/.config/`。

  这两个文件都是全局配置文件，如果都存在，将按照顺序读取。

- **`$GIT_DIR/config`**

  仓库特定的配置文件，作用范围为 **`local`** 。

- **`$GIT_DIR/config.worktree`**

​	可选的，且只有当 `$GIT_DIR/config` 中存在 `extensions.worktreeConfig` 时才会被搜索到。作用范围为 **`worktree`**

以上配置文件将从上到下依次读取，最后读取的值生效优先级最高。如果是多值键，将使用从所有文件中读取到的值。

除此之外，还可以在执行命令时通过 `-c` 选项指定配置参数，对应的配置范围为 **`command`**。

还可以通过 **`--file`** 选项指定读取或写入特定路径下的配置文件，或者通过  **`--system`**、**`--global`**、 **`--local`** 或者 **`--worktree`** 选项指定读取或修改对应范围的配置文件。

## include & includeIf

除了上面 5 种作用范围的配置之外，还可以通过 `include` 和 `includeIf` 从其他文件引入的配置指令，`include` 和 `includeIf` 的行为基本一样，唯一区别就是`includeIf` 可以提供一个条件，条件为 true 才会引入。

用法示例如下：

```
[include]
	      path = /path/to/foo.inc ; include by absolute path
	      path = foo.inc ; find "foo.inc" relative to the current file
	      path = ~/foo.inc ; find "foo.inc" in your `$HOME` directory

; include if $GIT_DIR is /path/to/foo/.git
[includeIf "gitdir:/path/to/foo/.git"]
	      path = /path/to/foo.inc

; include for all repositories inside /path/to/group
[includeIf "gitdir:/path/to/group/"]
	      path = /path/to/foo.inc

; include for all repositories inside $HOME/to/group
[includeIf "gitdir:~/to/group/"]
	      path = /path/to/foo.inc

; relative paths are always relative to the including
; file (if the condition is true); their location is not
; affected by the condition
[includeIf "gitdir:/path/to/group/"]
	      path = foo.inc

; include only if we are in a worktree where foo-branch is
; currently checked out
[includeIf "onbranch:foo-branch"]
	      path = foo.inc

; include only if a remote with the given URL exists (note
; that such a URL may be provided later in a file or in a
; file read after this file is read, as seen in this example)
[includeIf "hasconfig:remote.*.url:https://example.com/**"]
        path = foo.inc
```

**`includeIf`** 的条件以关键字开始，后跟冒号和一些数据，其格式和含义由关键字决定。支持以下四种关键字：

- **`gitdir`**

  `gitdir:` 后面的数据是使用标准的 glob 通配符以及 \*\*/ 和 /\*\* 的模式，写法可以参考 gitignore，如果仓库的 .git 目录的位置与模式匹配，则满足 include 条件。为了方便：

  - 如果模式以 ~/ 开头，~ 将被替换为 $HOME。
  - 如果模式以 ./ 开头，将被替换为包含当前配置文件的目录。
  - 如果模式不以 ~/、./ 或 / 开始，将自动在前面添加 \*\*/。例如，模式 foo/bar 变为 \*\*/foo/bar，将匹配 /any/path/to/foo/bar。
  - 如果模式以 / 结尾，将在末尾自动添加 \*\*。例如，模式 foo/ 变为 foo/\*\*。

- **`gitdir/i`**

  与 `gitdir` 相同，只是匹配不区分大小写。

- **`onbbranch`**

  `onbranch:` 后面的数据同样使用标准的 glob 通配符以及 \*\*/ 和 /\*\* 的模式，如果当前分支的名称与模式匹配，则满足 include 条件。

  同样如果模式以 / 结尾，将在末尾自动添加 \*\*。

- **`hasconfig:remote.*.url:`**

  关键字后面的数据也是使用标准的 glob 通配符以及 \*\*/ 和 /\*\* 的模式。当第一次发现此关键字时，将在剩余配置文件中查找 `remote URLs`。如果有一个 `remote URL` 与此模式匹配，则满足 include 条件。

  通过此选项（直接或间接）引入的文件不允许包含 `remote URL`。



通过 `includeIf` 可以轻松实现在不同的仓库之间实现多用户配置，而不用每个仓库单独重复配置同样的参数。