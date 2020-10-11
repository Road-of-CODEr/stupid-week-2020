## Leetcode

<img width="774" alt="leetcode" src="https://user-images.githubusercontent.com/46305139/95685012-40334f00-0c30-11eb-8f06-ddea01413a71.png">



#### [Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) (7/22)

- BFS

```kotlin
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
import java.util.*


class Solution {
    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()
        if (root == null) {
            return result
        }
        val que: Queue<TreeNode> = LinkedList()
        que.offer(root)
        var reverse = false
        while (que.isNotEmpty()) {
            val size = que.size
            val list = mutableListOf<Int>()
            for (i in 1..size) {
                val node = que.poll()
                node.left?.let { que.offer(it) }
                node.right?.let { que.offer(it) }
                if (reverse) {
                    list.add(0, node.`val`)
                } else {
                    list.add(node.`val`)
                }
            }
            reverse = !reverse
            result.add(list)
        }

        return result
    }
}
```



#### [Find Minimum in Rotated Sorted Array II](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/) (7/25)

```kotlin
class Solution {
    fun findMin(nums: IntArray): Int {
        var result = nums[0]
        for (num in nums) {
            if (num < result) {
                result = num
                break
            }
        }
        return result
    }
}
```

- greedy한 방식으로 풀이하였다.
- 다만, 다른 사람들의 경우에는 Binary Search를 이용하여 풀이를 하였는데 나의 방식이 모든 경우에 맞는 것인지는 의아하다. 난이도 또한 hard로 분류되어 있어 더더욱 의아하다.



#### [Add Digits](https://leetcode.com/problems/add-digits/) (7/26)

```kotlin
class Solution {
    fun addDigits(num: Int): Int {
        val list = num.toString().toCharArray().map { it - '0' }
        val que: Queue<Int> = LinkedList(list)

        var result = 0
        while (que.isNotEmpty()) {
            result += que.poll()
            if (result >= 10) {
                result %= 10
                que.add(1)
            }
        }
        return result
    }
}
```

- O(1) 풀이

```kotlin
class Solution {
    fun addDigits(num: Int): Int {
      	return if (num == 0) 0 else if (num % 9 == 0) 9 else num % 9
    }
}
```

- 수학을 이용하여 O(1)만의 풀이를 할 수 있다.
- 이 문제에서 기억하고 가야할 것 : **Digital Root**

