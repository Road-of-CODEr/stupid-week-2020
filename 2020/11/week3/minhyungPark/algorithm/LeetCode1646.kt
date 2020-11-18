/**
 * Leetcode 1646. Get Maximum in Generated Array
 * https://leetcode.com/contest/weekly-contest-214/problems/get-maximum-in-generated-array/
 */

class LeetCode1646 {

    fun getMaximumGenerated(n: Int): Int {
        val nums = IntArray(n+1)
        for (i in 2 until nums.size) {
            if (i%2==0) {
                nums[i] = nums[i/2]
            } else {
                nums[i] = nums[i/2] + nums[i/2 + 1]
            }
        }

        return nums.max()!!
    }
}