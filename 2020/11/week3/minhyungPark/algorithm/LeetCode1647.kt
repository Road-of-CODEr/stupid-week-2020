/**
 * Leetcode 1647. Minimum Deletions to Make Character Frequencies Unique
 * https://leetcode.com/contest/weekly-contest-214/problems/minimum-deletions-to-make-character-frequencies-unique/
 */

class LeetCode1647 {
    fun minDeletions(s: String): Int {
        val frequency = IntArray(26)
        s.forEach { c -> frequency[c-'a']++ }
        println(frequency.contentToString())

        val nums = IntArray(frequency.max()!! + 1)
        frequency.filter { it!=0 }.forEach { nums[it]++ }

        var result = 0
        for (i in nums.size-1 downTo 1) {
            if (nums[i] >= 2) {
                result+= nums[i] - 1
                nums[i-1] += nums[i] -1
            }
        }
        return result
    }
}
