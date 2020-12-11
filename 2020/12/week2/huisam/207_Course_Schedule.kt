package com.huisam.kotlin.problem

import java.util.*

class Solution4 {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val degrees = IntArray(numCourses) { 0 }
        val edges = MutableList(numCourses) { mutableListOf<Int>() }
        for (prerequisite in prerequisites) {
            edges[prerequisite[0]].add(prerequisite[1])
            degrees[prerequisite[1]]++
        }

        return topologicalSort(numCourses, degrees, edges)
    }

    private fun topologicalSort(
        numCourses: Int,
        degrees: IntArray,
        edges: MutableList<MutableList<Int>>
    ): Boolean {
        val queue = LinkedList<Int>()
        (0 until numCourses)
            .filter { degrees[it] == 0 }
            .forEach { queue.push(it) }

        for (i in 0 until numCourses) {
            if (queue.isEmpty()) {
                return false
            }

            val now = queue.pop()
            for (next in edges[now]) {
                degrees[next]--
                if (degrees[next] == 0) {
                    queue.push(next)
                }
            }
        }
        return true
    }
}

fun main() {
    println(Solution4().canFinish(2, arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))))
}