[Top K Frequent Elements](https://leetcode.com/explore/challenge/card/july-leetcoding-challenge/546/week-3-july-15th-july-21st/3393/)

- 7/17 July challenge

```kotlin
import java.util.*

class TopKFrequentElements {

    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val countMap = mutableMapOf<Int, Int>()
        for (num in nums) {
            countMap[num] = countMap.getOrDefault(num, 0) + 1
        }
        val priorityQueue = PriorityQueue<Pair<Int, Int>>{o1, o2 -> o2.second - o1.second }
        for ((num,count) in countMap) {
            priorityQueue.add(num to count)
        }
        return IntArray(k) {
            priorityQueue.poll().first
        }
    }
}
```



[Add Binary](https://leetcode.com/explore/challenge/card/july-leetcoding-challenge/546/week-3-july-15th-july-21st/3395/)

- 7/19 July challenge

```kotlin
import java.lang.StringBuilder

class AddBinary {

    fun addBinary(a: String, b: String): String {
        val sb = StringBuilder()
        var aPoint = a.length - 1
        var bPoint = b.length - 1
        var carry = 0

        while (aPoint >= 0 || bPoint >= 0) {
            var sum = carry
            if (aPoint >= 0) {
                sum += a[aPoint--] - '0'
            }
            if (bPoint >= 0) {
                sum += b[bPoint--] - '0'
            }
            carry = if (sum >=2) 1 else 0
            sb.append(sum % 2)
        }

        if (carry == 1) {
            sb.append(1)
        }
        return sb.toString().reversed()
    }
}
```

