class AbusingMap {
  fun solution(k: Int, score: IntArray): Int {
    val map = mutableMapOf<Int, Int>()
    val check = Array(score.size) { true }

    for ((index, value) in score.withIndex()) {
      if (index == 0) continue

      val diff = score[index - 1] - value
      map[diff] = map[diff]?.plus(1) ?: 1
    }
    val abusingMap = map.filterValues { it >= k }

    for ((index, value) in score.withIndex()) {
      if (index == 0) continue

      val diff = score[index - 1] - value
      if (abusingMap.containsKey(diff)) {
        check[index - 1] = false
        check[index] = false
      }
    }


    return check.count { it }
  }
}