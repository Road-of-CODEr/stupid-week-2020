/**
 * Leetcode 1641. Count Sorted Vowel Strings
 * https://leetcode.com/contest/weekly-contest-213/problems/count-sorted-vowel-strings/
 */

class LeetCode1641 {
    fun countVowelStrings(n: Int): Int {
        val dp = Array(n+1) { IntArray(6) }
        for (j in 1..5) {
            dp[0][j] = 1
        }

        for (i in 1..n) {
            for (j in 1..5) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1]
            }
        }
        return dp[n][5]
    }
}