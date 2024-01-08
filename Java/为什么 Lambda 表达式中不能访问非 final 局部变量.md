# Lambda 表达式和内部类中不能访问非 final 局部变量

>为什么Java中lambda表达式不能改变外部变量的值，也不能定义自己的同名的本地变量呢？ - blindpirate的回答 - 知乎
>https://www.zhihu.com/question/361639494/answer/948286842

局部变量存在于栈帧的局部变量表中，一旦方法结束，栈帧被销毁，这个变量（这份数据）就不再存在，但是该变量可能仍然需要在lamdba 或者内部类中使用。解决这个问题的办法就是将数据复制一份，为了保证两个副本之间的一致性，局部变量需被强制为“final”。

> 复制之后实际上是两份数据，但是会容易让人误以为是一份，强制为 final 能够降低心智负担。

至于为什么不想办法让 lamdba 表达式或者内部类能够使用可变的局部变量呢？因为可能引入更多的问题。

>  While we relax the syntactic restrictions on captured values, we still prohibit capture of mutable local variables. The reason is that idioms like this:
>
> ```
> int sum = 0;
> list.forEach(e -> { sum += e.size(); }); // ERROR
> ```
>
> are fundamentally serial; it is quite difficult to write lambda bodies like this that do not have race conditions. Unless we are willing to enforce -- preferably at compile time -- that such a function cannot escape its capturing thread, **this feature may well cause more trouble than it solves**. Lambda expressions close over *values*, not *variables*.



effectively final：没有声明为 final 但是只会被赋值一次的变量。
