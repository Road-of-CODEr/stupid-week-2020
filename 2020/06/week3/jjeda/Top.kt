class Solution {
  fun solution(heights: IntArray): IntArray {
    val list = mutableListOf<Top>()
    val topHeights = IntArray(heights.size + 1)

    list.add(Top.of(index = 1, targetIndex = 0))
    topHeights[0] = 101

    heights.withIndex().forEach { (index, height) ->
      topHeights[index + 1] = height
    }

    for (i in 2 until topHeights.size) {
      var receiveTopIndex = i - 1
      while (topHeights[receiveTopIndex] <= topHeights[i]) {
        receiveTopIndex = list[receiveTopIndex - 1].targetIndex
      }
      list.add(Top.of(index = i, targetIndex = receiveTopIndex))
    }
    return list.map { it.targetIndex }.toIntArray()
  }

  class Top(val index: Int, val targetIndex: Int) {
    companion object {
      fun of(index: Int, targetIndex: Int): Top {
        return Top(index, targetIndex)
      }
    }
  }

}