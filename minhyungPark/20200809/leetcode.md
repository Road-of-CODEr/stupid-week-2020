### Leetcode



**[Vertical Order Traversal of a Binary Tree](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/)**

```kotlin
class Solution {
    private val cache = mutableMapOf<Int, MutableList<Pair<Int ,Int>>>()
    fun verticalTraversal(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        root?.let { bfs(root) }
        val comparator = Comparator { o1: Pair<Int, Int>, o2: Pair<Int, Int> ->
            if (o1.second == o2.second) {
                o1.first - o2.first
            } else {
                o2.second - o1.second
            }
        }
        cache.keys.sorted().forEach{
            cache[it]!!.sortWith(comparator)
            result.add(cache[it]!!.map { pair -> pair.first })
        }
        return result
    }

    private fun bfs(node: TreeNode) {
        val que = LinkedList<Triple<TreeNode, Int, Int>>()
        que.offer(Triple(node, 0, 0))
        while (que.isNotEmpty()) {
            val current = que.poll()
            val curNode = current.first
            val x = current.second
            val y = current.third
            val list = cache[x] ?: ArrayList()
            list.add(Pair(curNode.`val`, y))
            cache[x] = list

            curNode.left?.let {que.offer(Triple(curNode.left!!, x-1, y -1))}
            curNode.right?.let {que.offer(Triple(curNode.right!!, x+1, y -1))}
        }
    }
}
```



**[Path Sum III](https://leetcode.com/problems/path-sum-iii/)**

```kotlin
class Solution {
    var pathCount = 0

    fun pathSum(root: TreeNode?, sum: Int): Int {
        pathCount = 0
        root?.let {
            dfs(it, sum, 0, false)
            dfs(it, sum, it.`val`, true)
        }
        return pathCount
    }

    private fun dfs(node: TreeNode, sum: Int, currentSum: Int, flag: Boolean) {
        if (currentSum == sum && flag) {
            pathCount++
        }

        node.right?.let {
            if (!flag) {
                dfs(it, sum, currentSum, false)
            }
            dfs(it, sum, currentSum + it.`val`, true)
        }
        
        node.left?.let {
            dfs(it, sum, currentSum + it.`val`, true)
            if (!flag) {
                dfs(it, sum, currentSum, false)
            }
        }

    }
}
```



**[Rotting Oranges](https://leetcode.com/problems/rotting-oranges/)**

```kotlin
class Solution {
    fun orangesRotting(grid: Array<IntArray>): Int {
        val que = LinkedList<Pair<Int, Int>>()

        for ((i, arr) in grid.withIndex()) {
            for ((j, num) in arr.withIndex()) {
                if (num == 2) {
                    que.add(Pair(i,j))
                }
            }
        }
        return bfs(que, grid)
    }

    private fun bfs(que: LinkedList<Pair<Int, Int>>, grid: Array<IntArray>): Int {
        var time = 0

        val n = grid.size
        val m = grid[0].size
        
        val dx = intArrayOf(0, 0, -1, 1)
        val dy = intArrayOf(1, -1, 0, 0)

        while (que.isNotEmpty()) {
            val size = que.size

            var flag = false
            for (i in 1..size) {
                val orange = que.poll()

                for (d in 0 until 4) {
                    val nx = orange.first + dx[d]
                    val ny = orange.second + dy[d]
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                        continue
                    }
                    if (grid[nx][ny] == 1) {
                        grid[nx][ny] = 2
                        que.offer(Pair(nx, ny))
                        flag = true
                    }
                }
            }
            if (flag) {
                time++
            }
        }

        if (!isAllRotting(grid)) {
            return -1
        }

        return time
    }

    private fun isAllRotting(grid: Array<IntArray>): Boolean {
        for (arr in grid) {
            for (num in arr) {
                if (num == 1) {
                    return false
                }
            }
        }
        return true
    }
}
```

