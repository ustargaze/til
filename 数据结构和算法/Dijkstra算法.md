# Dijkstra 算法

> 来源：https://oi-wiki.org/graph/shortest-path/#dijkstra-%E7%AE%97%E6%B3%95

求解非负权图上单源最短路径的算法。

## 过程

将节点分成两个集合：已确定最短路径长度的点集（记为 S 集合）和未确定最短路长度的点集（记为 T 集合）。一开始所有的点都属于 T 集合。

初始化 dis(s) = 0，其他点的 dis 均为 +∞。

重复以下操作：

1. 从 T 中，选取一个最短路长度最小的节点，移到 S 中。
2. 对刚加入 S 集合的节点的所有出边执行松弛操作。

知道 T 为空，算法结束。

## 时间复杂度

- 暴力：不实用任何数据结构进行维护，每次操作 2 执行完后，直接在 T 集合中暴力寻找最短路长度最小的节点。操作 2 总时间复杂度为 $O(m)$，操作 1 总时间复杂度为 $O(n^2)$，全过程的时间复杂度为 $O(m+n^2)=O(n^2)$。
- 优先队列：使用优先队列，如果同一个点的最短路被更新多次，因为先前更新时插入的元素不能被删除，也不能被修改，只能修改优先队列中，故优先队列内的元素个数是$O(m)$ 的，时间复杂度为 $O(m \log m)$

- ......

在稀疏图中 $m=O(n)$，而在稠密图中 $m=O(n^2)$，这时候使用暴力做法实现更优。



## 练习

- [743. 网络延迟时间](https://leetcode.cn/problems/network-delay-time/)
- [2642. 设计可以求最短路径的图类](https://leetcode.cn/problems/design-graph-with-shortest-path-calculator/)
- [1514. 概率最大的路径](https://leetcode.cn/problems/path-with-maximum-probability/)
- [1631. 最小体力消耗路径](https://leetcode.cn/problems/path-with-minimum-effort/)
- [1368. 使网格图至少有一条有效路径的最小代价](https://leetcode.cn/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/)
- [1786. 从第一个节点出发到最后一个节点的受限路径数](https://leetcode.cn/problems/number-of-restricted-paths-from-first-to-last-node/)
- [1976. 到达目的地的方案数](https://leetcode.cn/problems/number-of-ways-to-arrive-at-destination/)
- [2662. 前往目标的最小代价](https://leetcode.cn/problems/minimum-cost-of-a-path-with-special-roads/)
- [2045. 到达目的地的第二短时间](https://leetcode.cn/problems/second-minimum-time-to-reach-destination/)
- [882. 细分图中的可到达节点](https://leetcode.cn/problems/reachable-nodes-in-subdivided-graph/)
- [2203. 得到要求路径的最小带权子图](https://leetcode.cn/problems/minimum-weighted-subgraph-with-the-required-paths/)
- [2577. 在网格图中访问一个格子的最少时间](https://leetcode.cn/problems/minimum-time-to-visit-a-cell-in-a-grid/)
- [2699. 修改图中的边权](https://leetcode.cn/problems/modify-graph-edge-weights/)
- [LCP 35. 电动车游城市](https://leetcode.cn/problems/DFPeFJ/)

