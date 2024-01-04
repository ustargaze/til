# Gosper's Hack 算法

Gosper's Hack 是一种生成 *n* 元集合所有 *k* 元子集的算法。

如下：

```java
void GospersHack(int k, int n) {
    int cur = (1 << k) - 1;
    int limit = 1 << n;
    while (cur < limit) {
      
        // do something

        int lb = cur & -cur;
        int r = cur + lb;
        cur = (((r ^ cur) >> 2) / lb) | r;
        // or cur = ((r ^ cur) >> Integer.numberOfTrailingZeros(lb) + 2) | r;
    }
}
```



