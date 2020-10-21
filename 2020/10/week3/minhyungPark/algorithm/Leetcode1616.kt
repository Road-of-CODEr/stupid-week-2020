/**
 * Leetcode 1616 - Split Two Strings to Make Palindrome
 * https://leetcode.com/contest/weekly-contest-210/problems/split-two-strings-to-make-palindrome/
 */

class Leetcode1616 {
    fun checkPalindromeFormation(a: String, b: String): Boolean {
        return validate(a, b) || validate(b, a)
    }

    private fun validate(a: String, b: String): Boolean {
        var left = 0
        var right = a.length-1
        
        while (left < right && a[left] == b[right]) {
            left++
            right--
        }
        return validate(a, left, right) || validate(b, left, right)
    }

    private fun validate(s: String, left: Int, right: Int): Boolean {
        var l = left
        var r = right
        while (l < r && s[l] == s[r]) {
            l++
            r--
        }
        return l >= r
    }
}