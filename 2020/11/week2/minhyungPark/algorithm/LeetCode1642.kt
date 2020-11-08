/**
 * Leetcode 1642. Furthest Building You Can Reach
 * https://leetcode.com/contest/weekly-contest-213/problems/furthest-building-you-can-reach/
 */

import java.util.*

class LeetCode1642 {
    fun furthestBuilding(heights: IntArray, bricks: Int, ladders: Int): Int {
        val pq = PriorityQueue<Int>()
        var remainingBricks = bricks
        for (i in 0 until heights.size-1) {
            val diff = heights[i+1] - heights[i]
            if (diff > 0) {
                pq.add(diff)
            }
            if (pq.size > ladders) {
                remainingBricks -= pq.poll()
            }
            if (remainingBricks < 0) {
                return i
            }
        }
        return heights.size - 1
    }
}