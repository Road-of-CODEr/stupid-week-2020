/**
 * Leetcode 1662. Check If Two String Arrays are Equivalent
 * https://leetcode.com/contest/weekly-contest-216/problems/check-if-two-string-arrays-are-equivalent/
 */

class LeetCode1662 {
    fun arrayStringsAreEqual(word1: Array<String>, word2: Array<String>): Boolean {
        return word1.joinToString(separator = "") == word2.joinToString(separator = "")
    }
}