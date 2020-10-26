/**
 * Leetcode 1625. Lexicographically Smallest String After Applying Operations
 * https://leetcode.com/contest/weekly-contest-211/problems/lexicographically-smallest-string-after-applying-operations/
 */

class Leetcode1625 {

    private val set = mutableSetOf<String>()

    fun findLexSmallestString(s: String, a: Int, b: Int): String {
        dfs(s, a, b)
        return set.sorted()[0]
    }

    private fun dfs(s: String, a: Int, b: Int) {
        if (set.contains(s)) {
            return
        }
        set.add(s)

        dfs(add(s, a), a, b)
        dfs(rotate(s, b), a, b)
    }

    private fun add(s: String, a: Int) = s.withIndex().map { 
        if (it.index%2 == 0) {
            it.value
        } else {
            ((it.value - '0' + a) % 10 + '0'.toInt()).toChar()
        } 
    }.joinToString(separator = "")

    private fun rotate(s: String, b: Int) = s.substring(s.length - b) + s.substring(0, s.length - b)
}