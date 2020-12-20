package leetcode

class LeetCode1679 {
    fun maxOperations(nums: IntArray, k: Int): Int {
        var result = 0
        val countMap = mutableMapOf<Int, Int>()
        nums.forEach { countMap[it] = countMap.getOrDefault(it, 0) + 1 }
        for (num in nums) {
            if (countMap[num] == 0) continue
            else countMap[num] = countMap[num]!! - 1
            countMap[k-num]?.let {
                if (it != 0) {
                    result++
                    countMap[k-num] = it - 1
                }
            }
        }
        return result
    }
}