/**
 * Leetcode 1614 - Maximum Nesting Depth of the Parentheses
 * https://leetcode.com/contest/weekly-contest-210/problems/maximum-nesting-depth-of-the-parentheses/
 */

class Leetcode1614 {

    companion object {
        const val OPEN_PARENTHESES = '('
        const val CLOSE_PARENTHESES = ')'
    }

    fun maxDepth(s: String): Int {
        var currentDepth = 0
        
        var depth = 0
        for (i in s.indices) {
            if (s[i] == OPEN_PARENTHESES) {
                currentDepth++
            }
            if (s[i] == CLOSE_PARENTHESES) {
                currentDepth--
            }
            depth = if (depth < currentDepth) currentDepth else depth
        }
        return depth
    }
}