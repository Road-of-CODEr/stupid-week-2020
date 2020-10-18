/**
 * Leetcode 1615 - Maximal Network Rank
 * https://leetcode.com/contest/weekly-contest-210/problems/maximal-network-rank/
 */

class Leetcode1615 {
    fun maximalNetworkRank(n: Int, roads: Array<IntArray>): Int {
        
        val connected = Array(n) { BooleanArray(n) }
        val bridgeCount = IntArray(n)
        
        roads.forEach { 
            connected[it[0]][it[1]] = true
            connected[it[1]][it[0]] = true
            bridgeCount[it[0]]++
            bridgeCount[it[1]]++
        }
        
        var result = 0
        
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (i==j) continue
                val tmp = bridgeCount[i] + bridgeCount[j] - if (connected[i][j]) 1 else 0
                result = if (result < tmp) tmp else result
            }
        }
        
        return result
    }
}