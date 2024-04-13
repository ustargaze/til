# Z函数（扩展 KMP）

> from: https://oi-wiki.org/string/z-func/

## 定义

对于一个长度为 n 的字符串 s，定义函数 z[i] 表示 s 和 s[i, n - 1]（即以 s[i] 开头的后缀）的最长公共前缀（LCP）的长度，则 z 被称为 s 的 Z函数。特别地，z[0] = 0。

## 朴素算法

```java
public static int[] zFunctionTrivial(String s) {
    int n = s.length();
    int[] z = new int[n];
    for (int i = 1; i < n; i++) {
        while(i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) z[i]++;
    }
    return z;
}
```

时间复杂度为 $O(n^2)$。

## 线性算法

在该算法中，从 1 到 n - 1 顺次计算 $z[i]4$ 的值（$z[0] = 0$）。在计算 $z[i]$ 的过程中，会利用计算好的 $z[0], \dots, z[i-1]$。

对于 i，我们称区间 $[i, i+z[i]-1]$ 是 i 的匹配段，也可以叫 Z-box。

在算法的过程中维护右端点最靠右的匹配段。为了方便，记为 $[l, r]$。根据定义，$s[l, r]$ 是 s 的前缀。在计算 $z[i]$ 时保证 $l \le r$。初始时 $l=r=0$​。

在计算 $z[i]$ 的过程中：

- 如果 $i \le r$，那么根据 $[l, r]$ 的定义有 $s[i, r] = s[i - l, r - l]$，因此 $z[i] \ge min(z[i-l], r-i+1)$。这时：
  - 如果 $z[i-l] \lt r-i+1$，则 $z[i]=z[i-l]$​。
  
    > 如下所示（以下为举例，$z[i-l]$ 不是一定小于 $i-l$）：
    >
    > $$
    > \overbrace{\underbrace{s_{0} ~ \dots ~ s_{z[i-l]-1}}_{z[i-l]} ~ \dots ~ \underbrace{s_{i-l} ~ \dots ~ s_{i-l+z[i-l]-1}}_{z[i-l]}  ~ \dots ~ s_{z[k]-1}}^{z[k]} ~ \dots ~ \overbrace{\underset{l}{s_{k}} ~ \dots ~ \underbrace{s_{i} ~ \dots ~ s_{i+z[i-l]-1}}_{z[i-l]} ~ \dots ~ \underset{r}{s_{k+z[k]-1}} }^{z[k]} ~ \dots
    > $$
    > 有 $s[0,z[i-l]]=s[i-l, i-l+z[i-l]-1]=s[i,i+z[i-l]-1]$，所以 $z[i]=z[i-l]$。
  - 否则 $z[i-l] \ge r-i+1$，这时另 $z[i]=r-i+1$，然后暴力枚举下一个字符扩展 $z[i]$​ 直到不能扩展位置。
  
    > 如下所示：
    >
    > $$
    > \overbrace{\underbrace{s_{0} ~ \dots ~ s_{r-i+1}}_{r-i+1} ~ \dots ~ s_{z[i-l]-1}  }^{z[i-l]} ~ \dots \\
    > s_{0} ~ \dots ~ \overbrace{\underbrace{s_{i-l} ~ \dots ~ s_{z[k]-1}}_{r-i+1} ~ \dots ~ s_{i-l+z[i-l]-1}  }^{z[i-l]} ~ \dots \\
    > s_{0} ~ \dots ~ \underset{l}{s_{k}} ~ \dots ~  \overbrace{\underbrace{ s_{i} ~ \dots \underset{r}{s_{k+z[k]-1}} }_{r-i+1} ~ \dots ~ s_{i+z[i-l]-1} }^{z[i-l]} ~ \dots
    > $$
    > 因为 $z[i-l] \ge r-i+1$ 可以确定 $s[0,r-i+1]=s[i-l, z[k]-1]=s[i,k+z[k]-1]$，所以 $z[i]$ 最小为 $r-i+1$，还需要继续枚举看能够继续扩展。
- 如果 $i > r$，那么直接按照朴素算法，从 $s[i]$ 开始比较，暴力求出 $z[i]$。
- 在求出 $z[i]$ 后，如果 $i+z[i]-1 > r$，就需要更新 $[l, r]$，即令 $l=i, r=i+z[i]-1$​。

```java
public static int[] zFunction(String s) {
    int n = s.length();
    int[] z = new int[n];
    for (int i = 1, l = 0, r = 0; i < n; i++) {
        if (i <= r && z[i - l] < r - i + 1) {
            z[i] = z[i - l];
        } else {
            z[i] = Math.max(0, r - i + 1);
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) z[i]++;
        }
        if (i + z[i] - 1 > r) {
            l = i;
            r = i + z[i] - 1;
        }
    }
    return z;
}
```

对于内层 `while` 循环，每次执行都会使得 r 向后移至少 1 位，而 r <  n-1，所以总共只会执行 n 次。

对于外层循环，只有一遍线性遍历。

总复杂度为 $O(n)$。

## 应用

寻找在文本 t 中模式 p 的所有出现。

构造新字符串 $s = p + \diamond + t$，计算 s 的 Z 函数。如果在区间 $[0, |t|+1]$ 中存在 $z[i]=|p|$，可知 $i - |p| - 1$ 为 p 在 t 中的出现的位置。

```java
public static List<Integer> match(String t, String p) {
    int m = t.length(), n = p.length();
    String s = p + '#' + t;
    int[] z = zFunction(s);
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i <= m + 1; i++) {
        if (z[i] == n) {
            res.add(i - n - 1);
        }
    }
    return res;
}
```

时空间复杂度为 $O(m+n)$。

## 练习

- [3036. 匹配模式数组的子数组数目 II](https://leetcode.cn/problems/number-of-subarrays-that-match-a-pattern-ii/)
- [3008. 找出数组中的美丽下标 II](https://leetcode.cn/problems/find-beautiful-indices-in-the-given-array-ii/)
- [2223. 构造字符串的总得分和](https://leetcode.cn/problems/sum-of-scores-of-built-strings/) 2220
- [3031. 将单词恢复初始状态所需的最短时间 II](https://leetcode.cn/problems/minimum-time-to-revert-word-to-initial-state-ii/) 2278