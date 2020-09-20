```kotlin
import kotlin.math.abs

class Solution {
  companion object {
    const val LEFT_HAND = "left"
    const val RIGHT_HAND = "right"
    const val DEFAULT_ROW_INDEX = 3
    const val DEFAULT_DISTANCE = 1
  }

  fun solution(numbers: IntArray, hand: String): String {
    val result = StringBuilder()
    var leftHandRowIndex = 3
    var leftHandDistance = 1
    var rightHandRowIndex = 3
    var rightHandDistance = 1

    fun useLeftHand(targetRowIndex: Int, distance: Int) {
      leftHandRowIndex = targetRowIndex
      leftHandDistance = distance
      result.append('L')
    }

    fun useRightHand(targetRowInd: Int, distanceFromMiddle: Int) {
      rightHandRowIndex = targetRowInd
      rightHandDistance = distanceFromMiddle
      result.append('R')
    }

    numbers.forEach {
      when (it) {
        1, 4, 7 -> useLeftHand((it - 1) / DEFAULT_ROW_INDEX, DEFAULT_DISTANCE)
        3, 6, 9 -> useRightHand((it - 3) / DEFAULT_ROW_INDEX, DEFAULT_DISTANCE)
        else -> {
          val targetRowInd = (if (it == 0) 9 else it - 2) / DEFAULT_ROW_INDEX
          val leftDistance = leftHandDistance + abs(leftHandRowIndex - targetRowInd)
          val rightDistance = rightHandDistance + abs(rightHandRowIndex - targetRowInd)
          when {
            leftDistance < rightDistance -> useLeftHand(targetRowInd, 0)
            leftDistance > rightDistance -> useRightHand(targetRowInd, 0)
            hand == LEFT_HAND -> useLeftHand(targetRowInd, 0)
            hand == RIGHT_HAND -> useRightHand(targetRowInd, 0)
            else -> throw IllegalArgumentException()
          }
        }
      }
    }
    return result.toString()
  }
}
```