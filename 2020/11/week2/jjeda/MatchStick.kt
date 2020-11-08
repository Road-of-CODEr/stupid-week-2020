class MatchStick {
  private val cost = intArrayOf(6, 2, 5, 5, 4, 5, 6, 3, 7, 6)
  private val cache = Array(51) { 0 }

  fun solution(k: Int): Int {
    cost.forEach { requiredMatches ->
      cache[requiredMatches]++
    }
    cache[6]--

    for (i in 4..k) {
      cost.filter { it < i }
          .forEach { cache[i] += cache[i - it] }
    }
    cache[6]++
    return cache[k]
  }
}