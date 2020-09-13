## 결과
알고리즘 3문제

- 20201 카카오 1번
```kotlin
// TODO: Refactor with regex
class Solution {
  companion object {
    val validChar = setOf<Char>(
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_', '.')
  }

  fun solution(new_id: String): String {
    return process(new_id)
  }

  private fun process(id: String): String {
    // 1st
    val idToLowerCase = id.toLowerCase()

    // 2st
    val invalidSanitizedString = idToLowerCase.filter { validChar.contains(it) }

    // 3st
    val check = Array(invalidSanitizedString.length) { true }
    for (index in 1 until invalidSanitizedString.length) {
      if (invalidSanitizedString[index-1] == '.' && invalidSanitizedString[index] == '.') {
        check[index] = false
      }
    }
    val doublePeriodSanitizedString = invalidSanitizedString.filterIndexed { index, _ ->
      check[index]
    }

    // 4st
    val trimPeriod = doublePeriodSanitizedString.trim('.')

    // 5st
    val emptyCheckedString = if (trimPeriod.isEmpty()) {
      "a"
    } else {
      trimPeriod
    }

    // 6st
    val lengthCheckString = if (emptyCheckedString.length >= 16) {
      emptyCheckedString.substring(0, 15).trim('.')
    } else {
      emptyCheckedString
    }

    // 7st
    val finalCheck = when (lengthCheckString.length) {
      1 -> {
        lengthCheckString + lengthCheckString.last() + lengthCheckString.last()
      }
      2 -> {
        lengthCheckString + lengthCheckString.last()
      }
      else -> {
        lengthCheckString
      }
    }
    return finalCheck
  }
}
```


- 20201 카카오 2번
```kotlin
class Solution {
  companion object {
    val courseArray = Array<MutableMap<String, Int>>(11) { mutableMapOf() }
  }

  fun solution(orders: Array<String>, course: IntArray): Array<String> {
    val answerList = mutableListOf<String>()
    for (c in course) {
      orders.filter { order -> order.length >= c }
          .forEach { order ->
            makeKeyWithCombination(order, c).forEach {
              courseArray[c][it] = courseArray[c][it]?.plus(1) ?: 1
            }
          }
    }

    for (c in course) {
      val maxCount = courseArray[c].values.max()
      answerList.addAll(courseArray[c].filterValues {
        it == maxCount && it > 1
      }.map { it.key })
    }
    return answerList.sorted().toTypedArray()
  }

  private fun makeKeyWithCombination(order: String, r: Int): List<String> {
    val sortedOrder = order.toSortedSet().joinToString("")
    val returnList = mutableListOf<String>()
    val combinationArray = IntArray(order.length) { 0 }
    for (i in 0 until r) {
      combinationArray[i] = 1
    }

    do {
      returnList.add(sortedOrder.filterIndexed { index, _ -> combinationArray[index] == 1 })
    } while (prevPermutation(combinationArray))

    return returnList
  }

  private fun prevPermutation(list: IntArray): Boolean {
    var i = list.size - 1;
    var j = list.size - 1;

    while (i > 0 && list[i - 1] <= list[i]) i--
    if (i <= 0) return false

    while (list[i - 1] <= list[j]) j--
    var temp = list[i - 1]
    list[i - 1] = list[j]
    list[j] = temp

    var k = list.size - 1;
    while (i < k) {
      temp = list[i]
      list[i] = list[k]
      list[k] = temp
      i++
      k--
    }
    return true
  }
}
```


- 20201 카카오 3번
```kotlin
class Solution {
  companion object {
    val languageArray = arrayOf("cpp", "java", "python")
    val jobArray = arrayOf("backend", "frontend")
    val positionArray = arrayOf("junior", "senior")
    val soulFoodArray = arrayOf("chicken", "pizza")
  }

  private fun makeInfoKey(info: String): String {
    return info.filter { it in 'a'..'z' }
  }

  private fun makeValue(info: String): Int {
    return info.filter { it in '0'..'9' }.toInt()
  }

  private fun queryKey(query: String): List<String> {
    val languageList = mutableListOf<String>()
    val st = StringTokenizer(query)

    val language = st.nextToken()
    if (language == "-") {
      languageArray.forEach { languageList.add(it) }
    } else {
      languageList.add(language)
    }
    st.nextToken()

    val jobList = mutableListOf<String>()
    val job = st.nextToken()
    if (job == "-") {
      jobArray.forEach {
        languageList.forEach { lang ->
          jobList.add(lang + it)
        }
      }
    } else {
      languageList.forEach {
        jobList.add(it + job)
      }
    }
    st.nextToken()

    val positionList = mutableListOf<String>()
    val position = st.nextToken()
    if (position == "-") {
      positionArray.forEach {
        jobList.forEach { job ->
          positionList.add(job + it)
        }
      }
    } else {
      jobList.forEach {
        positionList.add(it + position)
      }
    }
    st.nextToken()

    val soulFoodList = mutableListOf<String>()
    val soulFood = st.nextToken()
    if (soulFood == "-") {
      soulFoodArray.forEach {
        positionList.forEach { position ->
          soulFoodList.add(position + it)
        }
      }
    } else {
      positionList.forEach {
        soulFoodList.add(it + soulFood)
      }
    }

    return soulFoodList
  }

  fun solution(info: Array<String>, query: Array<String>): IntArray {
    val database = mutableMapOf<String, MutableList<Int>>()

    info.forEach {
      val key = makeInfoKey(it)
      val value = makeValue(it)
      if (database.containsKey(key)) {
        database[key]?.add(value)
      } else {
        database[key] = mutableListOf(value)
      }
    }

    return query.map {
      val keys = queryKey(it)
      val value = makeValue(it)

      keys.sumBy { key ->
        database[key]?.count { v -> v >= value } ?: 0
      }
    }.toIntArray()
  }
}
```