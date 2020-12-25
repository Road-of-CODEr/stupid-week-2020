package com.huisam.kotlin.problem

/**
 * LeetCode Problem
 * @see <a href="https://leetcode.com/problems/missing-number/">문제링크</a>
 */
class Solution6 {
    fun missingNumber(nums: IntArray): Int {
        val unCheckedNumbers = HashSet<Int>(nums.size + 1).apply {
            repeat(nums.size + 1) {
                add(it)
            }
        }
        unCheckedNumbers.removeAll(nums.toMutableList())
        return unCheckedNumbers.last()
    }
}

fun main() {
    println(Solution6().missingNumber(intArrayOf(3, 0, 1)))
}