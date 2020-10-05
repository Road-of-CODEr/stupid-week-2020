## Objective - 1st Oct

1. [leetcode](#algo1)
2. [1% network 1장](./one-percent-net-1.md)
3. [성묘 다녀오기](./assets/성묘.jpeg)


####  <a name="algo1"></a> [Split a String Into the Max Number of Unique Substrings](https://leetcode.com/contest/weekly-contest-207/problems/split-a-string-into-the-max-number-of-unique-substrings/)

```kotlin
import kotlin.math.max

class Solution {
    fun maxUniqueSplit(s: String): Int {
        return dfs(s, mutableSetOf())
    }

    private fun dfs(s: String, set: MutableSet<String>): Int {
        var result = 0
        
        for (i in 1..s.length) {
            val candidate = s.substring(0, i)
            if(!set.contains(candidate)) {
                set.add(candidate)
                result = max(result , 1 + dfs(s.substring(i), set))
                set.remove(candidate)
            }
        }
        return result
    }
}
```