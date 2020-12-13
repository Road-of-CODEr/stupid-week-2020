class LeetCode1672 {

    fun maximumWealth(accounts: Array<IntArray>): Int {
        return accounts.map { it.sum() }.max()!!
    }
}