- [Word Break II](https://leetcode.com/problems/word-break-ii/)

```kotlin
class Solution {
    private val cacheMap: MutableMap<String, List<String>> = mutableMapOf()

    fun wordBreak(s: String, wordDict: List<String>): List<String> {
        cacheMap[s]?.let { return@wordBreak it }

        val result = mutableListOf<String>()
        if (wordDict.contains(s)) {
            result.add(s)
        }

        for (i in 1 until s.length) {
            val leftString = s.substring(0, i)
            if (wordDict.contains(leftString)) {
                val list = wordBreak(s.substring(i), wordDict)
                for (element in list) {
                    result.add("$leftString $element")
                }
            }
        }
        cacheMap[s] = result
        return result
    }
}
```



- [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)

```kotlin
class Solution {
        fun climbStairs(n: Int): Int {
        val dp = IntArray(n+2)
        dp[1] = 1
        dp[2] = 2
        
        for (i in 3..n) {
            dp[i] = dp[i-1] + dp[i-2]
        }
        return dp[n]
    }

}
```



- [Design HashSet](https://leetcode.com/problems/design-hashset/)

```kotlin
class MyHashSet() {

    private val hashArray: BooleanArray = BooleanArray(100001)

    fun add(key: Int) {
        hashArray[key] = true
    }
    
    fun remove(key: Int) {
        hashArray[key] = false
    }
    
    fun contains(key: Int): Boolean {
        return hashArray[key]
    }

}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * var obj = MyHashSet()
 * obj.add(key)
 * obj.remove(key)
 * var param_3 = obj.contains(key)
 */
```

