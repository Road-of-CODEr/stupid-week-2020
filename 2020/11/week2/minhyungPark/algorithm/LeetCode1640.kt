/**
 * Leetcode 1640. Check Array Formation Through Concatenation
 * https://leetcode.com/contest/weekly-contest-213/problems/check-array-formation-through-concatenation/
 */

class LeetCode1640 {
    fun canFormArray(arr: IntArray, pieces: Array<IntArray>): Boolean {
        val arrString = arr.joinToString(prefix = ".",postfix = ".",separator = ".")
        return pieces.all { arrString.contains(it.joinToString(prefix = ".",postfix = ".",separator = ".")) }
    }
}