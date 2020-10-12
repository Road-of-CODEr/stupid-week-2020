## 결과
알고리즘 3문제 

- [블록 이동하기](https://programmers.co.kr/learn/courses/30/lessons/60063)
```kotlin
package oct.second

import java.util.LinkedList
import java.util.Queue

class MovingBlock {
  private val check = mutableSetOf<Block>()
  private var size = 0

  fun solution(board: Array<IntArray>): Int {
    size = board.size
    return findMinimumMovingCount(board)
  }

  private fun findMinimumMovingCount(board: Array<IntArray>): Int {
    val startingPoint = Block(Coordinate(0, 0) to Coordinate(0, 1))
    val firstJob = Job(startingPoint, 0)
    val queue: Queue<Job> = LinkedList()
    var answer = 0
    queue.add(firstJob)
    check.add(firstJob.block)

    while (queue.isNotEmpty()) {
      val currentJob = queue.poll()

      if (isGoal(currentJob.block)) {
        answer = currentJob.count
        break
      }
      queue.addAll(findNextJob(currentJob, board))
    }
    return answer
  }

  private fun isGoal(block: Block): Boolean {
    val (firstCoordinate, secondCoordinate) = block.coordinates

    if ((firstCoordinate.row == size - 1 && firstCoordinate.col == size - 1) ||
        (secondCoordinate.row == size - 1 && secondCoordinate.col == size - 1)) {
      return true
    }
    return false
  }

  private fun findNextJob(currentJob: Job, board: Array<IntArray>): List<Job> {
    val jobs = mutableListOf<Job>()
    jobs.addAll(moveOneBlock(currentJob.block, board).map { Job(it, currentJob.count + 1) })
    jobs.addAll(rotateBlock(currentJob.block, board).map { Job(it, currentJob.count + 1) })
    jobs.forEach { check.add(it.block) }
    return jobs
  }

  private fun moveOneBlock(currentBlock: Block, board: Array<IntArray>): List<Block> {
    val direction = mapOf(
      Direction.EAST to (0 to 1), Direction.SOUTH to (1 to 0),
      Direction.WEST to (0 to -1), Direction.NORTH to (-1 to 0)
    )

    operator fun Block.plus(other: Pair<Int, Int>) =
        Block(
          Coordinate(this.coordinates.first.row + other.first, this.coordinates.first.col + other.second) to
              Coordinate(this.coordinates.second.row + other.first, this.coordinates.second.col + other.second)
        )

    return direction.mapNotNull { (_, coordinate) ->
      val block = currentBlock + coordinate
      if (!isMoveAble(block, board)) {
        return@mapNotNull null
      }
      block
    }.toList()
  }

  private fun isMoveAble(block: Block, board: Array<IntArray>): Boolean {
    val (firstCoordinate, secondCoordinate) = block.coordinates

    if (check.contains(block)) {
      return false
    }

    try {
      if (
          board[firstCoordinate.row][firstCoordinate.col] == 1 ||
          board[secondCoordinate.row][secondCoordinate.col] == 1) {
        return false
      }
    } catch (e: ArrayIndexOutOfBoundsException) {
      return false
    }
    return true
  }

  private fun rotateBlock(currentBlock: Block, board: Array<IntArray>): List<Block> {
    // TODO: Refactor
    val blocks = mutableListOf<Block>()
    val (firstCoordinate, secondCoordinate) = currentBlock.coordinates

    if (firstCoordinate.row == secondCoordinate.row) {
      if (secondCoordinate.row > 0 && board[secondCoordinate.row - 1][secondCoordinate.col] == 0 &&
          board[firstCoordinate.row - 1][firstCoordinate.col] == 0) {
        blocks.add(Block(firstCoordinate to Coordinate(firstCoordinate.row - 1, firstCoordinate.col)))
      }
      if (secondCoordinate.row < size - 1 && board[secondCoordinate.row + 1][secondCoordinate.col] == 0 &&
          board[firstCoordinate.row + 1][firstCoordinate.col] == 0) {
        blocks.add(Block(firstCoordinate to Coordinate(firstCoordinate.row + 1, firstCoordinate.col)))
      }
      if (firstCoordinate.row > 0 && board[firstCoordinate.row - 1][secondCoordinate.col] == 0 &&
          board[secondCoordinate.row - 1][secondCoordinate.col] == 0) {
        blocks.add(Block(secondCoordinate to Coordinate(secondCoordinate.row - 1, secondCoordinate.col)))
      }
      if (firstCoordinate.row < size - 1 && board[firstCoordinate.row + 1][firstCoordinate.col] == 0 &&
          board[secondCoordinate.row + 1][secondCoordinate.col] == 0) {
        blocks.add(Block(secondCoordinate to Coordinate(secondCoordinate.row + 1, secondCoordinate.col)))
      }
    } else {
      if (secondCoordinate.col > 0 && board[secondCoordinate.row][secondCoordinate.col - 1] == 0 &&
          board[firstCoordinate.row][firstCoordinate.col - 1] == 0) {
        blocks.add(Block(firstCoordinate to Coordinate(firstCoordinate.row, firstCoordinate.col - 1)))
      }
      if (secondCoordinate.col < size - 1 && board[secondCoordinate.row][secondCoordinate.col + 1] == 0 &&
          board[firstCoordinate.row][firstCoordinate.col + 1] == 0) {
        blocks.add(Block(firstCoordinate to Coordinate(firstCoordinate.row, firstCoordinate.col + 1)))
      }
      if (firstCoordinate.col > 0 && board[firstCoordinate.row][firstCoordinate.col - 1] == 0 &&
          board[secondCoordinate.row][secondCoordinate.col - 1] == 0) {
        blocks.add(Block(secondCoordinate to Coordinate(secondCoordinate.row, secondCoordinate.col - 1)))
      }
      if (firstCoordinate.col < size - 1 && board[firstCoordinate.row][firstCoordinate.col + 1] == 0 &&
          board[secondCoordinate.row][secondCoordinate.col + 1] == 0) {
        blocks.add(Block(secondCoordinate to Coordinate(secondCoordinate.row, secondCoordinate.col + 1)))
      }
    }

    return blocks.filter { !check.contains(it) }
  }

  data class Coordinate(val row: Int, val col: Int)

  data class Block(val coordinates: Pair<Coordinate, Coordinate>)

  data class Job(val block: Block, val count: Int)

  enum class Direction {
    EAST, SOUTH, WEST, NORTH
  }
}

fun main() {
  val solution = MovingBlock()
  println(solution.solution(arrayOf(intArrayOf(0,0,0,1,1), intArrayOf(0,0,0,1,0), intArrayOf(0,1,0,1,1), intArrayOf(1,1,0,0,
    1), intArrayOf(0,0,0,0,0))))
}
```
- 윈터코딩 2번 
```kotlin
class Solution2 {
  fun solution(p: String, n: Int): String {
    val (type, time) = p.split(' ')
    val (hour, min, sec) = time.split(':')
    var customTime = hour.toInt() * 3600 + min.toInt() * 60 + sec.toInt() + n % 57600

    if (type == TYPE.PM.toString()) {
      customTime += 12 * 3600
    }
    var newHour = customTime / 3600
    newHour %= 24
    val newHourString = if (newHour < 10) "0$newHour" else newHour.toString()
    customTime %= 3600
    val newMin = customTime / 60
    val newMinString = if (newMin < 10) "0$newMin" else newMin.toString()
    customTime %= 60
    val newSec = customTime
    val newSecString = if (newSec < 10) "0$newSec" else newSec.toString()

    return "$newHourString:$newMinString:$newSecString"
  }
  enum class TYPE {
    AM, PM
  }
}
```
- 윈터코딩 4번 
```kotlin
class Solution {
  private val map = mutableMapOf<String, Int>()
  private val map2 = mutableMapOf<Int, MutableList<String>>()

  private lateinit var sorted: List<Pair<Int, MutableList<String>>>

  fun solution(votes: Array<String>, k: Int): String {
    votes.forEach {
      map[it] = map[it]?.plus(1) ?: 1
    }

    map.forEach { (key, value) ->
      if (map2.containsKey(value)) {
        map2[value]?.add(key)
      } else {
        map2[value] = mutableListOf(key)
      }
    }
    sorted = map2.toList().sortedBy { it.first }
    sorted.forEach {
      it.second.sortDescending()
    }
    val reversed = sorted.reversed()
    var voteCount = 0
    var copyK = k
    loop@ for ((vote, cars) in reversed) {
      for (car in cars) {
        if (copyK > 0) {
          voteCount += vote
          copyK--
        } else {
          break@loop
        }
      }
    }

    var prevCar = ""
    for (i in sorted.indices) {
      for (car in sorted[i].second) {
        if (voteCount - sorted[i].first <= 0) {
          return prevCar
        }
        voteCount -= sorted[i].first
        prevCar = car
      }
    }
    return ""
  }
}
```
