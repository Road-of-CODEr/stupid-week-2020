## 결과
알고리즘 3문제

1. 카카오 4번 다시풀어보기
```kotlin
class Solution {

  fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
    val logList = mutableListOf<Pair<Int, Int>>()
    val process = mutableSetOf<Int>()
    logs.map {
      val (start, end) = it.split('-')
      process.add(logToSec(start))
      logList.add(Pair(logToSec(start), logToSec(end)))
    }
    val advSec = logToSec(adv_time)
    var max = 0
    var index = 0
    process.add(0)
    process.sorted().forEach {
      var count = 0
      val last = it + advSec
      logList.filter { pair ->
        pair.first < last && pair.second > it
      }.forEach { pair ->
        if (it >= pair.first && pair.second > last) {
          count += (pair.second - it)
        } else if (pair.first > it && pair.second < last) {
          count += (pair.second - pair.first)
        } else if (pair.first < last && pair.second > last) {
          count += (last - pair.first)
        }
      }
      if (max < count) {
        max = count
        index = it
      }
    }
    return secToLog(index)
  }

  private fun logToSec(log: String): Int {
    val (hour, minute, second) = log.split(':')
    return (hour.toInt() * 3600) + (minute.toInt() * 60) + second.toInt()
  }

  private fun secToLog(sec: Int): String {
    var log = ""
    val hours = sec / 3600
    val minutes = (sec % 3600) / 60
    val seconds = (sec % 3600) % 60

    log += if (hours < 10) {
      "0${hours}:"
    } else {
      "${hours}:"
    }

    log += if (minutes < 10) {
      "0${minutes}:"
    } else {
      "${minutes}:"
    }

    log += if (seconds < 10) {
      "0${seconds}"
    } else {
      seconds.toString()
    }
    return log
  }
}

fun main() {
  val solution = Solution4()
  print(
      solution.solution(
          "02:03:55",
          "00:14:15",
          arrayOf(
              "01:20:15-01:45:14",
              "00:40:31-01:00:00",
              "00:25:50-00:48:29",
              "01:30:59-01:53:29",
              "01:37:44-02:02:30"
          )
      )
  )
}

```
1. [프로그래머스_보석쇼핑](https://programmers.co.kr/learn/courses/30/lessons/67258)
```kotlin
class Solution {
    fun solution(gems: Array<String>): IntArray {
        val answer = intArrayOf(1, gems.size)
        val tokenKindCount = gems.toSet().size
        gems.foldIndexed(LinkedHashMap<String, Int>()) { index, gemMap, gem ->
            gemMap.remove(gem)
            gemMap[gem] = index

            if (gemMap.size == tokenKindCount) {
                val start = gemMap.values.first()
                val end = gemMap.values.last()
                if (end - start < answer[1] - answer[0]) {
                    answer[0] = start + 1
                    answer[1] = end + 1
                }
            }
            gemMap
        }

        return answer
    }
}
``` 
1. [프로그래머스_불량사용](https://programmers.co.kr/learn/courses/30/lessons/64064)
```kotlin
class Solution {
    var matchInfo = mutableSetOf<Set<String>>()

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        val bannedIds = banned_id.asSequence()
            .map { it.replace("*", ".") }
            .map { Regex(it) }
            .toList()
        search(user_id.toList(), bannedIds)
        return matchInfo.size
    }

    private fun search(
        userIds: List<String>,
        bannedIdRegexList: List<Regex>,
        matchedIds: List<String> = listOf()
    ) {
        if (bannedIdRegexList.isEmpty()) {
            matchInfo.add(matchedIds.toSet())
            return
        }
        if (userIds.isEmpty()) {
          return
        }
        val bannedIdRegex = bannedIdRegexList[0]
        for (userId in userIds) {
            if (bannedIdRegex.matches(userId)) {
                search(userIds - userId, bannedIdRegexList.drop(1), matchedIds + userId)
            }
        }
    }
}
```