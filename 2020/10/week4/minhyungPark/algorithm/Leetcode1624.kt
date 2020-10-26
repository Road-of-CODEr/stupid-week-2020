/**
 * Leetcode 1624. Largest Substring Between Two Equal Characters
 * https://leetcode.com/contest/weekly-contest-211/problems/largest-substring-between-two-equal-characters/
 */

class Leetcode1624 {

    fun maxLengthBetweenEqualCharacters(s: String): Int {
        val arr = Array<MutableList<Int>>(26) { mutableListOf() }
        s.withIndex().forEach { arr[it.value-'a'].add(it.index) }
        return arr.filter { it.size >= 2 }.map { it[it.size - 1] - it[0] - 1 }.max() ?: -1
    }
}