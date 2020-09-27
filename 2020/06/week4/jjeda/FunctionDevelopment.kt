class Solution {
  fun solution(progresses: IntArray, speeds: IntArray): IntArray {
    val progressDay = IntArray(100)
    var day = -1

    progresses.withIndex().forEach { (index, value) ->
      while (value + (day * speeds[index]) < 100) {
        day++
      }
      progressDay[day]++
    }
    return progressDay.filter { it > 0 }.toIntArray()
  }
}