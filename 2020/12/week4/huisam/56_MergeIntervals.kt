package com.huisam.kotlin.problem

/**
 * LeetCode Problem
 * @see <a href="https://leetcode.com/problems/merge-intervals/">문제링크</a>
 */
class Solution5 {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortWith { o1, o2 -> o1[0].compareTo(o2[0]) }
        val answer = mutableListOf<IntArray>()

        for (interval in intervals) {
            if (answer.isEmpty() || answer.last()[1] < interval[0]) {
                answer.add(interval)
            } else {
                answer.last()[1] = answer.last()[1].coerceAtLeast(interval[1])
            }
        }

        return answer.toTypedArray()
    }
}

fun main() {
    println(
        Solution5().merge(arrayOf(intArrayOf(5, 6), intArrayOf(1, 4))).contentToString()
    )
}