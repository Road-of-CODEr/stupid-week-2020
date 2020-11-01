/**
 * Leetcode 1630. Arithmetic Subarrays
 * https://leetcode.com/contest/weekly-contest-212/problems/arithmetic-subarrays/
 */

class Leetcode1630 {

    fun checkArithmeticSubarrays(nums: IntArray, l: IntArray, r: IntArray): List<Boolean> {
        val res = mutableListOf<Boolean>()
        for (i in l.indices) {
            val subArray = nums.slice(l[i]..r[i]).sorted()
            res.add(
                subArray.withIndex()
                    .all { it.index ==0 || subArray[1] - subArray[0] == subArray[it.index] - subArray[it.index-1] }
            )
        }
        return res
    }
}