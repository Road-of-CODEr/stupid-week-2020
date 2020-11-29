class Solution {
    fun reverseString(s: CharArray): Unit {
        var temp: Char
        var limit = if (s.size % 2 == 0) s.size / 2 - 1 else s.size / 2
        for (i in 0..limit) {
            temp = s[i];
            s[i] = s[s.size - 1 - i]
            s[s.size - 1 - i] = temp
        }
    }
}