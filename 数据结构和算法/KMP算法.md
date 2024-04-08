# KMP 算法

> from: https://oi-wiki.org/string/kmp/

## 前缀函数

**定义**

给定一个长度为 n 的字符串 s，其前缀函数被定义为一个长度为 n 的数组 $\pi$。其中 $\pi[i]$ 的定义是：

1. 如果子串 $s[0 \dots i]$ 有一对相等的真前缀和真后缀：$s[0 \dots j-1]$ 和 $s[i-(j-1) \dots j-1]$，那么 $\pi[i]$ 就是这个相等的真前缀的长度，也就是 $\pi[i]=j$；
2. 如果不止有一对相等的，那么 $\pi[i]$ 就是其中最长的那一对的长度；
3. 如果没有相等的，那么 $\pi[i]=0$。

简单来说 $\pi[i]$ 就是，子串 $s[0 \dots i]$ 最长的相等的真前缀和真后缀的长度。

用数学语言描述：$ \pi[i] = \max_{j = 0 \dots i}\{j: s[0 \dots j-1] = s[i - (j-1) \dots i]\} $。特别地，规定 $\pi[0]=0$。

## 计算前缀函数的朴素算法

```java
public static int[] prefixFunction(String s) {
    int n = s.length();
    int[] pi = new int[n];
    for (int i = 1; i < n; i++) {
        for (int j = i; j >= 0; j--) {
            if (s.substring(0, j).equals(s.substring(i - j + 1, i + 1))) {
                pi[i] = j;
                break;
            }
        }
    }
    return pi;
}
```

时间复杂度为 $O(n^3)$。

## 计算前缀函数的高效算法

### 第一个优化

通过观察可知**相邻的前缀函数至多增加 1。**只需要考虑，当取一个尽可能大的 $\pi[i + 1]$ 时，必然要求新增的 $s[i+1]$ 也与之对应的字符匹配，即 $s[i+1]=s[\pi[i]]$，此时 $\pi[i+1]=\pi[i]+1$。

移动到下一个位置时，前缀函数的值要么增加 1，要么维持不变，要么减少。所以 j 的值只需要从 $\pi[i-1] + 1$ 开始，不需要从 i 开始。

```java
public static int[] prefixFunction(String s) {
    int n = s.length();
    int[] pi = new int[n];
    for (int i = 1; i < n; i++) {
        for (int j = pi[i - 1] + 1; j >= 0; j--) {
            if (s.substring(0, j).equals(s.substring(i - j + 1, i + 1))) {
                pi[i] = j;
                break;
            }
        }
    }
    return pi;
}
```

时间复杂度为 $O(n^2)$​。

> 对于这个时间复杂度的计算存有疑问，没想明白。

### 第二个优化

在第一个优化中讨论了计算 $\pi[i+1]$ 时的最好情况：$s[i+1]=s[\pi[i]]$，此时 $\pi[i+1]=\pi[i]+1$。

现在讨论 $s[i+1] \neq s[\pi[i]]$ 时如何跳转。

失配时，希望找到对于子串 $s[0 \dots i]$，仅次于 $\pi[i]$ 的第二长度 j，是的位置 i 的前缀性质仍得以保持，也即 $s[0 \dots j-1]$ 和 $s[i-(j-1) \dots j-1]$：
$$
\overbrace{\underbrace{s_0 ~ s_1}_j ~ s_2 ~ s_3}^{\pi[i]} ~ \dots ~ \overbrace{s_{i-3} ~ s_{i-2} ~ \underbrace{s_{i-1} ~ s_{i}}_j}^{\pi[i]} ~ s_{i+1}
$$


如果找到这样的长度 j，那么近需要再次比较 $s[i+1]$ 和 $s[j]$。如果他们相等，那么就有 $\pi[i+1]=j+1$。否则，就需要找到子串 $s[0 \dots i]$ 仅次于 j 的第二长度 $j^{(2)}$，使得前缀性质得以保持，如此返回，知道 j = 0。如果 $s[i+1] \neq s[0]$，则 $\pi[i+1]=0$。

通过观察上图可以，因为 $s[0\dots \pi[i]-1] = s[i-\pi[i]+1\dots i]$，所以对于 $s[0 \dots i]$ 的第二长度 j，有这样的性质：
$$
s[0 \dots j - 1] = s[i - j + 1 \dots i]= s[\pi[i]-j\dots \pi[i]-1]
$$
也就是说 j 等价于子串 $s[\pi[i]-1]$ 的前缀函数值，即 $j=\pi[\pi[i]-1]$。同理，次 j 的第二长度等价于 $s[j-1]$ 的前缀函数值。

因此可以得到关于 j 的状态转移方程：$j^{(n)}=\pi[j^{(n-1)}-1], \ \ (j^{(n-1)}>0)$

### 最终算法

```java
public static int[] prefixFunction(String s) {
    int n = s.length();
    int[] pi = new int[n];
    for (int i = 1, j = 0; i < n; i++) {
        while (j > 0 && s.charAt(j) != s.charAt(i)) {
            j = pi[j - 1];
        }
        if (s.charAt(j) == s.charAt(i)) {
            j++;
        }
        pi[i] = j;
    }
    return pi;
}
```

时间复杂度为 $O(n)$。

## KMP 算法过程

给定一个文本 t 和 s，尝试找到并展示 s 在 t 中的所有出现。以 n 表示 s 的长度，m 表示 t 的长度。

构造一个字符串 s + # + t 记为 S，其中 # 为一个既不出现在 s 中也不出现在 t 中的分隔符。接下来计算该字符串的前缀函数。

考虑从 n+1 开始的函数值的意义，当函数值为 n 时，意味着在子串 $S[0 \dots i]$ 中有长度为 n 的真前缀和真后缀，真前缀即为 s，真后缀即为 t 中出现的 s。计算 $S[0 \dots i]$ 长度为 n 的真后缀的起始位置减去 n+1 即为 s 在 t 中出现的起始位置，即 $i - (n-1) - (n+1)=i-2n$​​。

KMP 实现如下：

```java
public static List<Integer> kmp(String s1, String s2) {
    int m = s1.length(), n = s2.length();
    String S = s2 + '#' + s1;
    int[] pi = prefixFunction(S);
    List<Integer> res = new ArrayList<>();
    for (int i = n + 1; i < m + n + 1; i++) {
        if (pi[i] == n) {
            res.add(i - 2 * n);
        }
    }
    return res;
}
```

时间复杂度为 $O(m+n)$。



也可以不合并字符串，只计算 s 的前缀函数进行匹配。

```java
public static List<Integer> kmp(String s1, String s2) {
    int m = s1.length(), n = s2.length();
    int[] pi = prefixFunction(s2);
    List<Integer> res = new ArrayList<>();
    for (int i = 0, j = 0; i < m; i++) {
        while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
            j = pi[j - 1];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            j++;
        }
        if (j == n) {
            res.add(i - n + 1);
            j = 0;
        }
    }
    return res;
}
```

