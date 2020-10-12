## Objective - 2nd Oct

1. leetcode
2. leetcode
3. [OKR 작성](./OKR.md)

---

1. [Special Array With X Elements Greater Than or Equal X](https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/)

```kotlin
class Solution {
    fun specialArray(nums: IntArray): Int {
        val max = nums.max() ?: return 0
        
        (1..max).forEach { x -> 
            if (nums.filter { it >= x }.size == x) {
                return x
            }
        }
        
        return -1
    }
}
```

2. [Even Odd Tree](https://leetcode.com/problems/even-odd-tree/)

```kotlin
class Solution {
    fun isEvenOddTree(root: TreeNode?): Boolean {

        var level = 0
        val que = LinkedList<TreeNode>()
        que.offer(root)
        while (que.isNotEmpty()) {
            val size = que.size
            val list = mutableListOf<Int>()
            for (i in 1..size) {
                val element = que.poll()
                list.add(element.`val`)
                element.left?.let { que.offer(it) }
                element.right?.let { que.offer(it) }
            }
            if (!isOrdered(list, level%2)) {
                return false
            }
            
            if(list.any { it%2 == level%2 }) {
                return false
            }
            level++
        }
        return true
    }

    private fun isOrdered(list: List<Int>, orderIndex: Int): Boolean {
        val size = list.size
        
        var prev = list[0]
        for (idx in 1 until size) {
            val next = list[idx]
            if (orderIndex == 0 && next <= prev) {
                return false
            }
            if (orderIndex == 1 && next >= prev) {
                return false
            }
            prev = next
        }
        return true
    }

}
```
