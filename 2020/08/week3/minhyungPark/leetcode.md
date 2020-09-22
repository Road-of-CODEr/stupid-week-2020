**[Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers/submissions/)**

```kotlin
class Solution {

    fun sumNumbers(root: TreeNode?): Int {

        val leafNodeSet = mutableListOf<Int>()
        root?.let {dfs(it, leafNodeSet, it.`val`)}
        var sum = 0
        for (node in leafNodeSet) {
            sum += node
        }
        return sum
    }

    private fun dfs(currentTreeNode: TreeNode, leafNodeSet: MutableList<Int>, curNum: Int) {

        if (isLeaf(currentTreeNode)) {
            leafNodeSet.add(curNum)
        }

        currentTreeNode.left?.let {dfs(it, leafNodeSet, curNum*10 + it.`val`)}

        currentTreeNode.right?.let {dfs(it, leafNodeSet, curNum*10 + it.`val`)}

    }

    private fun isLeaf(node: TreeNode): Boolean {
        return node.left == null && node.right == null
    }
}

```



**[Surrounded Regions](https://leetcode.com/problems/surrounded-regions/)**

```kotlin
import java.util.*

class Solution {
    private val dx  = intArrayOf(-1, 1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)

    fun solve(board: Array<CharArray>): Unit {
        val n = board.size
        val m = if (n==0) 0 else board[0].size
        if (n == 0 || m == 0) return
        val visited = Array(n) { BooleanArray(m) }
        
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (i != 0 && j!=0 && i != n-1 && j != m-1) continue
                if (visited[i][j]) continue
                if (board[i][j]=='O') {
                    bfs(board, visited, i, j, n, m)
                }
            }
        }
        
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X'
                }
            }
        }
        
    }

    private fun bfs(board: Array<CharArray>, visited: Array<BooleanArray>, x: Int, y: Int, n: Int, m: Int) {
        val queue: Queue<Pair<Int,Int>> = LinkedList<Pair<Int, Int>>()
        visited[x][y] = true
        queue.offer(x to y)
        
        while (queue.isNotEmpty()) {
            val pair = queue.poll()
            
            for (i in 0 until 4) {
                val nx = pair.first + dx[i]
                val ny = pair.second + dy[i]
                if (nx < 0 || ny<0 || nx >= n || ny >= m) continue
                if (visited[nx][ny]) continue
                if (board[nx][ny] != 'O') continue
                visited[nx][ny] = true
                queue.offer(nx to ny)
            }
        }
        
    }
}
```



