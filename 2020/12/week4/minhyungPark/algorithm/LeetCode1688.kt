class LeetCode1688 {
    fun numberOfMatches(n: Int): Int {
        var num = n
        var res = 0
        while (num/2 >= 2) {
            res += (num/2)
            num = (num/2) + (num % 2)
        }
        return res
    }
}