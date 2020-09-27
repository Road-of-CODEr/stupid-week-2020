## 결과
알고리즘 6문제
- [프로그래머스-프린터](https://programmers.co.kr/learn/courses/30/lessons/42587)
```kotlin
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

data class Job(val index: Int, val value: Int)

/*
 * priorityQueue 와 jobQueue 룰 각각 생성하여
 * jq 와 pq가 일치하면 counting 일치하지 않으면 뒤로 넣기
 * 일치했을 때 location 이면 바로 return
 */
class Solution {
  fun solution(priorities: IntArray, location: Int): Int {
    val priorityQueue = PriorityQueue<Int>(Comparator.reverseOrder())
    val jobQueue: Queue<Job> = LinkedList()
    var count = 1

    priorityQueue.addAll(priorities.asList())  // 9 1 1 1 1 1
    priorities.forEachIndexed { index, value ->
      jobQueue.add(Job(index, value)) // 1 1 9 1 1 1
    }

    while (!jobQueue.isEmpty()) {
      val peek = jobQueue.peek()
      if (peek.value == priorityQueue.peek()) {
        if (peek.index == location) {
          return count
        }
        count++
        priorityQueue.poll()
        jobQueue.poll()
      } else {
        jobQueue.add(jobQueue.poll())
      }
    }
    return count
  }
}


```
- [프로그래머스-H_index](https://programmers.co.kr/learn/courses/30/lessons/42747)
```java
// 함수형 x
class Solution {
  public int solution(int[] citations) {
    int size = citations.length;

    for (int i = size - 1; i > 0; i--) {
      int count = 0;
      for (int citation : citations) {
        if (citation >= i) {
          count++;
        }
      }
      if (count >= i) {
        return i;
      }
    }
    return 0;
  }
}
``` 
```kotlin
// TODO: sequence와 first를 쓰면 for-if 중첩을 피하고 lazy evaluation 활용할 수 있음 
class Solution {
  fun solution(citations: IntArray): Int {
    val size = citations.size


    for (i in size downTo 1) {
      if (citations.filter { it >= i }.size >= i) {
        return i
      }
    }
    return 0
  }
}  
   
```


- [프로그래머스-위장](https://programmers.co.kr/learn/courses/30/lessons/42578?language=kotlin)
```kotlin
class Solution {
  fun solution(clothes: Array<Array<String>>): Int {
    val clothMap = mutableMapOf<String, MutableList<String>>()
    clothes.forEach { cloth ->
      val (item, index)  = cloth
      if (clothMap.containsKey(index)) {
        clothMap[index]?.add(item)
      } else {
        clothMap[index] = mutableListOf(item)
      }
    }
    var result = 1
    clothMap.keys.forEach { key ->
      result *= clothMap[key]?.size?.plus(1) ?: 1
    }
    return result - 1
  }
}
```

- [프로그래머스-튜플](https://programmers.co.kr/learn/courses/30/lessons/64065)
```kotlin
class Solution {
  fun solution(s: String): IntArray {
    val splitedStrings = splitString(s)
    val result = mutableListOf(splitedStrings.first().toInt())
    splitedStrings.windowed(2, 1) { window ->
      val (first, last) = window
      result.add((last - first).toInt())
    }
    return result.toIntArray()
  }

  private fun splitString(s: String): List<Set<String>> {
    val splitedStrings = s.trim('{', '}').split('{').map { it.trim(',', '}') }
    return splitedStrings
        .map { str -> str.split(',').toSet() }
        .sortedBy { it.size }
  }

  private fun Set<String>.toInt() = this.first().toInt()
}
```

- [프로그래머스-후보키](https://programmers.co.kr/learn/courses/30/lessons/42890)
```kotlin
class Solution {
  
  fun solution(relation: Array<Array<String>>): Int {
    return Database(relation.map {
      Record(it.toList())
    }).getUniqueKeys().size
  }

  data class Database(val records: List<Record>) {

    fun getUniqueKeys(): Set<Key> {
      val masterKey = Key(List(records.first().columns.size) { it }.toSet())
      return findAllUniqueKeys(masterKey)
    }

    private fun findAllUniqueKeys(key: Key): Set<Key> {
      return if (!definedUniquelyBy(key)) {
        emptySet()
      } else {
        key.subKeys()
            .flatMap(::findAllUniqueKeys).toSet()
            .takeIf { it.isNotEmpty() } ?: setOf(key)
      }
    }

    private fun definedUniquelyBy(key: Key): Boolean {
      return records.groupBy { record ->
        // pick only record's columns defined by key
        record.columns.filterIndexed { index, _ -> index in key.columns }
      }.size == records.size
    }
  }

  data class Record(val columns: List<String>)

  data class Key(val columns: Set<Int>) {

    fun subKeys(): List<Key> = columns.map { Key(columns - it) }
  }
}
```


- [프로그래머스-예상대진표](https://programmers.co.kr/learn/courses/30/lessons/12985)
```kotlin
import kotlin.math.ceil

class Solution {
  fun solution(n: Int, a: Int, b: Int): Int {
    var first = a
    var second = b
    var count = 0

    while (first != second) {
      first = ceil(first / 2.0).toInt()
      second = ceil(second / 2.0).toInt()
      count++
    }
    return count
  }
}
```