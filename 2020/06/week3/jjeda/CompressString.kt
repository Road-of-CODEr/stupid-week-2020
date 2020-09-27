class Solution {
  fun solution(s: String): Int {
    return (1..s.length / 2).map { s.compressLengthOf(it) }.min() ?: s.length
  }

  private fun String.compressLengthOf(n: Int): Int {
    val list = mutableListOf(0 to this.substring(0, n))

    this.chunked(n).forEach {
      val latestValue = list.last()
      if (latestValue.second != it) {
        list.add(1 to it)
        return@forEach
      }
      list[list.lastIndex] = latestValue.first + 1 to latestValue.second
    }

    return list.fold(0) { sum, word ->
      sum + word.second.length + countLength(word.first)
    }
  }

  private fun countLength(num: Int): Int {
    return when (num) {
      1 -> 0
      else -> num.toString().length
    }
  }
}