import kotlin.math.max
import kotlin.math.min

class LeetCode1690 {

    companion object {
        lateinit var pre : IntArray
        lateinit var dp: Array<Array<IntArray>>
    }

    fun stoneGameVII(stones: IntArray): Int {
        val n = stones.size
        dp = Array(2) { Array(n) { IntArray(n) { -1 } } }
        pre = IntArray(n+1)
        for (i in stones.indices) {
            pre[i+1] = pre[i] + stones[i]
        }
        return dfs(0, n-1, 1)
    }

    private fun dfs(
        left: Int,
        right: Int,
        isAlice: Int
    ): Int {
        if (left > right) return 0
        if (dp[isAlice][left][right] == -1) {
            memo(isAlice, left, right)
        }
        return dp[isAlice][left][right]
    }

    private fun memo(isAlice: Int, left: Int, right: Int) {
        if (isAlice == 1) {
            val a = dfs(left + 1, right, (isAlice + 1) % 2) + getSum(left + 1, right)
            val b = dfs(left, right - 1, (isAlice + 1) % 2) + getSum(left, right-1)
            dp[isAlice][left][right] = max(a, b)
        } else {
            val a = dfs(left + 1, right, (isAlice + 1) % 2) - getSum(left + 1, right)
            val b = dfs(left, right - 1, (isAlice + 1) % 2) - getSum(left, right-1)
            dp[isAlice][left][right] = min(a, b)
        }
    }

    private fun getSum(left: Int, right: Int) = pre[right+1] - pre[left]
}

fun main() {
    LeetCode1690().stoneGameVII(intArrayOf(5,3,1,4,2))
}