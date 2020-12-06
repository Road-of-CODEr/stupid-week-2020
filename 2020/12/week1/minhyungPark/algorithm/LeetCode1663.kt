/**
 * Leetcode 1663. Smallest String With A Given Numeric Value
 * https://leetcode.com/contest/weekly-contest-216/problems/smallest-string-with-a-given-numeric-value/
 */

class LeetCode1663 {
    fun getSmallestString(n: Int, k: Int): String {
        var remainValue = k
        val stringBuilder = StringBuilder()

        for (i in n downTo 1) {
            val currentMaxValue = remainValue - i + 1
            val numericValue = if (currentMaxValue > 26) 26 else currentMaxValue

            stringBuilder.append(numericValue.toAlphabet())
            remainValue -= numericValue
        }
        return stringBuilder.reverse().toString()
    }

    private fun Int.toAlphabet() = (this - 1 + 'a'.toInt()).toChar()

}