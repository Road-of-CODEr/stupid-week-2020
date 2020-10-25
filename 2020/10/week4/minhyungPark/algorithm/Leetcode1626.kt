/**
 * Leetcode 1626. Best Team With No Conflicts
 * https://leetcode.com/contest/weekly-contest-211/problems/best-team-with-no-conflicts/
 */

import kotlin.math.max

class Leetcode1626 {
    fun bestTeamScore(scores: IntArray, ages: IntArray): Int {
        val players = List(scores.size) { ages[it] to scores[it] }.sortedWith(compareBy({it.first}, {it.second})).reversed()
        val dp = IntArray(scores.size)

        for (i in players.indices) {
            val score = players[i].second
            dp[i] = score
            for (j in 0 until i) {
                if (players[j].second >= players[i].second) {
                    dp[i] = max(dp[i], dp[j] + score)
                }
            }
        }
        return dp.max()!!
    }
}
