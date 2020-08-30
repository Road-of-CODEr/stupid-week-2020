**[Implement Rand10() Using Rand7()](https://leetcode.com/problems/implement-rand10-using-rand7/)**

```kotlin
/**
 * The rand7() API is already defined in the parent class SolBase.
 * fun rand7(): Int {}
 * @return a random integer in the range 1 to 7
 */
class Solution : SolBase() {
    fun rand10(): Int {
        val col = rand7()
        val row = rand7()
        val idx = col + (row-1)*7
        return if (idx<=40) idx % 10 + 1 else rand10()
    }
}
```



**[Pancake Sorting](https://leetcode.com/problems/pancake-sorting/)**

```kotlin
class Solution {
    fun pancakeSort(A: IntArray): List<Int> {
        val result = mutableListOf<Int>()
        var point = A.size
        while (!isSorted(A)) {
            val idx = findIndex(A, point)
            if (idx != 1) {
                result.add(idx)
                reverse(A, idx)
            }
            result.add(point)
            reverse(A, point)
            --point
        }
        return result
    }

    private fun reverse(arr: IntArray, idx: Int){
        val temp = mutableListOf<Int>()
        for (i in idx-1 downTo 0) {
            temp.add(arr[i])
        }
        for ((i, num) in temp.withIndex()) {
            arr[i] = num
        }
    }

    private fun findIndex(arr: IntArray, target: Int): Int {
        for ((idx, element) in arr.withIndex()) {
            if (element == target) {
                return idx + 1
            }
        }
        return 0
    }


    private fun isSorted(arr: IntArray): Boolean {
        for ((idx, element) in arr.withIndex()) {
            if (element != idx+1) {
                return false
            }
        }
        return true
    }
}
```

