**[Image Overlap](https://leetcode.com/problems/image-overlap/)**

```kotlin
class Solution {

    fun largestOverlap(A: Array<IntArray>, B: Array<IntArray>): Int {
        val aList = mutableListOf<Pair<Int, Int>>()
        val bList = mutableListOf<Pair<Int, Int>>()

        for ((i, arr) in A.withIndex()) {
            for ((j, element) in arr.withIndex()) {
                if (A[i][j] == 1) {
                    aList.add(Pair(i,j))
                }
                if (B[i][j] == 1) {
                    bList.add(Pair(i,j))
                }
            }
        }
        val cache = mutableMapOf<Pair<Int, Int>, Int>()

        for (pa in aList) {
            for (pb in bList) {
                val key = Pair((pa.first - pb.first) , (pa.second - pb.second))
                cache[key] = cache.getOrDefault(key, 0) + 1
            }
        }

        var result = 0
        for (count in cache.values) {
            result = if (result < count) count else result
        }
        return result
    }

}
```



**[All Elements in Two Binary Search Trees](https://leetcode.com/problems/all-elements-in-two-binary-search-trees/)**

```kotlin
class Solution {
    fun getAllElements(root1: TreeNode?, root2: TreeNode?): List<Int> {
        val elements = mutableListOf<Int>()
        val que = LinkedList<TreeNode>()
        root1?.let { que.offer(it) }
        root2?.let { que.offer(it) }
        while (que.isNotEmpty()) {
            val curNode = que.poll()
            elements.add(curNode.`val`)
            curNode.left?.let { que.offer(it) }
            curNode.right?.let { que.offer(it) }
        }
        elements.sort()
        return elements
    }
}
```

