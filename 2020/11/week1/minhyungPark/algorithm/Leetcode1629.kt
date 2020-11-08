/**
 * Leetcode 1629. Slowest Key
 * https://leetcode.com/contest/weekly-contest-212/problems/slowest-key/
 */
import kotlin.math.max

class Leetcode1629 {

    fun slowestKey(releaseTimes: IntArray, keysPressed: String): Char {
        val durations = mutableMapOf<Char, Int>()
        for (i in releaseTimes.indices) {
            val time = if (i == 0) releaseTimes[i] else releaseTimes[i] - releaseTimes[i-1]
            durations[keysPressed[i]] = max(durations.getOrDefault(keysPressed[i], 0), time)
        }
        return durations.map { it.key to it.value }.sortedWith(compareBy({it.second}, {it.first})).last().first
    }
}