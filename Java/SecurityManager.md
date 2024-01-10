# Security Manager

> 使用和介绍参考：https://juejin.cn/post/6844903657775824910

security manager 默认是关闭的，如果要开启使用有两种方式。

1. 编码方式启动

   ```java
   System.setSecurityManager(new SecurityManager());
   ```

2. 启动参数

   启动 java 进程时添加 `-Djava.security.manager` 参数，可以给此参数指定一个自定义安全管理器类文件，例如：`-Djava.security.manager=MySecurityManager`。同时可以使用 `-Djava.security.policy` 参数来制定权限策略文件。





知道就行，没有必要花太多时间深入了解，因为安全管理器已在 java 17 中被标记为弃用，并将在未来的版本中移除，且没有替代品。

> 参见：https://openjdk.org/jeps/411

被移除的原因主要是，使用率太低且维护成本高。使用率低的原因主要一下三点：

1. 脆弱的权限模型：无法进行部分安全性访问控制，只能授予应用程序执行全部操作所需要的全部权限。
2. 困难的编程模型：基于 1，如果应用程序引入其他库，同样要授予库所需要的全部权限（并且库的开发者也需要记录库所需要的权限），但是这些权限可能不是应用程序所需要的，这就违反了最小权限原则，会造成权限病毒式增长。如果要防止这种情况同时遵循其他安全编码原则，那应用开发的心智成本太高了。
3. 性能差：安全管理器的核心是一个复杂的访问控制算法，会带来不可接受的性能损失。

>  After decades of maintaining the Security Manager but seeing very little usage, we are no longer willing to bear this ongoing and expensive burden.

