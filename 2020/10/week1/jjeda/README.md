## 결과

1. [자물쇠와 열쇠](https://programmers.co.kr/learn/courses/30/lessons/60059)
```kotlin
import java.lang.IndexOutOfBoundsException
import kotlin.math.abs

class KeyAndLock {
  fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
    Direction.values().forEach {
      for (row in key.indices) {
        for (col in key.indices) {
          val transformedKey = moveKey(rotateKey(key, it), row, col, lock.size)
          if (matchKey(transformedKey, lock)) {
            println("$row, $col, $it")
            return true
          }
        }
      }
    }
    return false
  }

  private fun rotateKey(key: Array<IntArray>, direction: Direction) = when (direction) {
    Direction.NORMAL -> key
    Direction.CLOCK_WISE -> rotateClockWise(key)
    Direction.ANTI_CLOCK_WISE -> rotateAntiClockWise(key)
    Direction.OPPOSITE -> rotateOpposite(key)
  }

  private fun rotateClockWise(key: Array<IntArray>): Array<IntArray> {
    val length = key.size
    val rotatedKey = Array(length) { IntArray(length) { 0 } }
    for (i in key.indices) {
      for (j in key.indices) {
        rotatedKey[i][j] = key[length - 1 - j][i]
      }
    }
    return rotatedKey
  }

  private fun rotateAntiClockWise(key: Array<IntArray>): Array<IntArray> {
    val length = key.size
    val rotatedKey = Array(length) { IntArray(length) { 0 } }
    for (i in key.indices) {
      for (j in key.indices) {
        rotatedKey[i][j] = key[j][length - 1 - i]
      }
    }
    return rotatedKey
  }

  private fun rotateOpposite(key: Array<IntArray>): Array<IntArray> {
    val length = key.size
    val rotatedKey = Array(length) { IntArray(length) { 0 } }
    for (i in key.indices) {
      for (j in key.indices) {
        rotatedKey[i][j] = key[length - 1 - i][length - 1 - j]
      }
    }
    return rotatedKey
  }

  private fun moveKey(key: Array<IntArray>, row: Int, col: Int, size: Int): Array<IntArray> {
    val movedKey = Array(size) { IntArray(size) { 0 } }
    if (abs(row) >= size || abs(col) >= size) {
      return movedKey
    }

    for (i in key.indices) {
      for (j in key.indices) {
        try {
          movedKey[i + row][j + col] = key[i][j]
        } catch (error: IndexOutOfBoundsException) {
          continue
        }
      }
    }
    return movedKey
  }

  private fun matchKey(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
    return lock.mapIndexed { rowIndex, row ->
      row.mapIndexed { colIndex, value ->
        value.xor(key[rowIndex][colIndex])
      }.sum()
    }.sum() == lock.size * lock.size
  }

  enum class Direction {
    NORMAL,
    CLOCK_WISE,
    ANTI_CLOCK_WISE,
    OPPOSITE
  }
}
```

2. [기둥과 보 설치](https://programmers.co.kr/learn/courses/30/lessons/60061)
```kotlin
class ColumnAndBeam {
  private lateinit var columnMap: Array<Array<Structure>>
  private lateinit var beamMap: Array<Array<Structure>>
  private var size: Int = 0

  fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
    size = n + 1
    columnMap = Array(size) { Array(size) { Structure.EMPTY } }
    beamMap = Array(size) { Array(size) { Structure.EMPTY } }


    build_frame.forEach {
      process(it)
    }
    return sortResult()
  }

  private fun process(buildFrame: IntArray) {
    val (x, y) = buildFrame
    val columnOrBeam = if (buildFrame[2] == 0) {
      Structure.COLUMN
    } else {
      Structure.BEAM
    }
    val createOrDelete = if (buildFrame[3] == 0) {
      CreateOrDelete.DELETE
    } else {
      CreateOrDelete.CREATE
    }

    if (!isPossible(x, y, columnOrBeam, createOrDelete)) return

    when (createOrDelete) {
      CreateOrDelete.CREATE -> addStructure(x, y, columnOrBeam)
      CreateOrDelete.DELETE -> removeStructure(x, y, columnOrBeam)
    }
    return
  }

  private fun isPossible(
      x: Int, y: Int, structure: Structure, createOrDelete: CreateOrDelete
  ): Boolean = when (createOrDelete) {
    CreateOrDelete.CREATE -> isCreatePossible(x, y, structure)
    CreateOrDelete.DELETE -> isDeletePossible(x, y, structure)
  }

  private fun isCreatePossible(x: Int, y: Int, structure: Structure): Boolean {
    when (structure) {
      Structure.COLUMN -> {
        if (y == 0 || columnMap[x][y - 1] == Structure.COLUMN ||
            (x > 0 && beamMap[x - 1][y] == Structure.BEAM) || (x < size - 1 && beamMap[x][y] == Structure.BEAM)) {
          return true
        }
      }
      Structure.BEAM -> {
        if (columnMap[x][y - 1] == Structure.COLUMN || columnMap[x + 1][y - 1] == Structure.COLUMN ||
            (x > 0 && beamMap[x - 1][y] == Structure.BEAM && beamMap[x + 1][y] == Structure.BEAM)) {
          return true
        }
      }
      else -> throw IllegalArgumentException()
    }
    return false
  }

  private fun isDeletePossible(x: Int, y: Int, structure: Structure): Boolean {


    var check = true
    removeStructure(x, y, structure)
    for (i in 0 until size) {
      for (j in 0 until size) {
        if (columnMap[i][j] == Structure.COLUMN) {
          check = isCreatePossible(i, j, Structure.COLUMN)
          if (!check) {
            addStructure(x, y, structure)
            return check
          }
        }

        if (beamMap[i][j] == Structure.BEAM) {
          check = isCreatePossible(i, j, Structure.BEAM)
          if (!check) {
            addStructure(x, y, structure)
            return check
          }
        }
      }
    }
    addStructure(x, y, structure)
    return check
  }

  private fun addStructure(x: Int, y: Int, structure: Structure) {
    when (structure) {
      Structure.COLUMN -> {
        columnMap[x][y] = Structure.COLUMN
      }
      Structure.BEAM -> {
        beamMap[x][y] = Structure.BEAM
      }
      else -> throw IllegalArgumentException()
    }
  }

  private fun removeStructure(x: Int, y: Int, structure: Structure) {
    when (structure) {
      Structure.COLUMN -> {
        columnMap[x][y] = Structure.EMPTY
      }
      Structure.BEAM -> {
        beamMap[x][y] = Structure.EMPTY
      }
      else -> throw IllegalArgumentException()
    }
  }

  private fun sortResult(): Array<IntArray> {
    val list = mutableListOf<IntArray>()
    for (x in 0 until size) {
      for (y in 0 until size) {
        if (columnMap[x][y] == Structure.COLUMN) {
          list.add(intArrayOf(x, y, 0))
        }

        if (beamMap[x][y] == Structure.BEAM) {
          list.add(intArrayOf(x, y, 1))
        }
      }
    }
    return list.sortedWith(compareBy<IntArray> { it.first() }.thenBy { it[1] }.thenBy { it[2] }).toTypedArray()
  }

  enum class Structure {
    EMPTY, COLUMN, BEAM
  }

  enum class CreateOrDelete {
    CREATE, DELETE
  }
}
```

3. [외벽 점검](https://programmers.co.kr/learn/courses/30/lessons/60062)
```kotlin
class ExternalWall {
  private lateinit var ascendingDist: IntArray
  private lateinit var weak: IntArray
  private lateinit var check: BooleanArray
  private var n = 0
  private var threshold = Int.MAX_VALUE

  fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
    this.weak = weak.copyOf()
    this.ascendingDist = dist.reversedArray()
    this.n = n
    this.check = BooleanArray(weak.size) { false }
    calculateThreshold(0)
    return if (threshold == Int.MAX_VALUE) -1 else threshold
  }

  private fun calculateThreshold(index: Int) {
    if (index >= threshold) {
      return
    }

    if (check.all { it }) {
      threshold = index
      return
    } else if (index == ascendingDist.size) {
      return
    }

    val copyCheck = check.copyOf()
    for ((i, start) in weak.withIndex()) {
      if (check[i]) continue
      inspectWeak(calculateArea(start, ascendingDist[index]))
      calculateThreshold(index + 1)
      check = copyCheck.copyOf()
    }
  }

  private fun calculateArea(start: Int, dist: Int): List<Int> {
    if (start + dist >= n) {
      return (0..(dist - (n - start))).toList() + (start until n).toList()
    }
    return (start..start + dist).toList()
  }

  private fun inspectWeak(area: List<Int>) {
    weak.forEachIndexed { index, value ->
      if (area.contains(value)) {
        check[index] = true
      }
    }
  }
}
```