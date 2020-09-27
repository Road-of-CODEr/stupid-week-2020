class Solution {
  fun solution(N: Int, stages: IntArray): IntArray {
    val failMap = mutableMapOf<Int, Float>()
    for (i in 1..N) {
      val successCount = stages.count { stage -> stage > i }
      val reachCount = stages.count { stage -> stage >= i }
      failMap[i] = (reachCount - successCount).toFloat() / reachCount.toFloat()
    }
    val sortedFailMap = failMap.toList().sortedBy { -1 * it.second }
    return sortedFailMap.toTypedArray()
        .map { pair -> pair.first }.toIntArray()
  }
}