/**
 * Leetcode 1631. Path With Minimum Effort
 * https://leetcode.com/contest/weekly-contest-212/problems/path-with-minimum-effort/
 */
import kotlin.math.abs
import kotlin.math.max

class Leetcode1631 {
    companion object {
        val dx = intArrayOf(-1, 1, 0, 0)
        val dy = intArrayOf(0, 0, -1, 1)
    }
    fun minimumEffortPath(heights: Array<IntArray>): Int {
        val n = heights.size
        val m = heights[0].size
        val visit = Array(n) { IntArray(m) { Int.MAX_VALUE } }

        val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.first })
        pq.offer(Triple(0,0,0))

        while (pq.isNotEmpty()) {
            val (height, x, y) = pq.poll()
            if (x == n-1 && y == m-1) {
                return height
            }
            for (i in dx.indices) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue
                }
                val newHeight = max(height, abs(heights[nx][ny] - heights[x][y]))
                if (newHeight >= visit[nx][ny]) {
                    continue
                }
                visit[nx][ny] = newHeight
                pq.offer(Triple(newHeight, nx, ny))
            }
        }

        return 0
    }
}
