**Two sum**

```kotlin
class TwoSum {

    fun twoSum(nums: IntArray, target: Int): IntArray {
        val cacheMap = mutableMapOf<Int, Int>()

        for ( (idx,num) in nums.withIndex()) {
            val diff = target - num

            cacheMap[diff]?.let { return@twoSum intArrayOf(it, idx) }
            cacheMap[num] = idx
        }
        return intArrayOf()
    }
}
```



**Add two numbers**

```kotlin
class AddTwoNumbers {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val list = mutableListOf<Int>()
        var carry = 0
        var left = l1
        var right = l2
        while (left != null || right != null || carry != 0) {
            var sum = carry
            sum+= left?.`val` ?: 0 
            sum+= right?.`val` ?: 0
            carry = if (sum >= 10) 1 else 0 
            list.add(sum%10)
            left = left?.next
            right = right?.next
        }
        val result: ListNode = ListNode(list[0])
        var temp = result
        for ((index,num) in list.withIndex()) {
            if (index == 0) continue
            temp.next = ListNode(num)
            temp = temp.next!!
        }
        return result
    }
}
```





