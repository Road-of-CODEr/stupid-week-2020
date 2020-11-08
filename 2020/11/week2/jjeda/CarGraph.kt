import java.util.LinkedList
import java.util.Queue

class CarGraph {
  private val map = mutableMapOf<String, MutableList<String>>()

  fun solution(depar: String, hub: String, dest: String, roads: Array<Array<String>>): Int {
    roads.forEach {
      if(map.containsKey(it[1])) {
        map[it[1]]?.add(it[0])
      } else {
        map[it[1]] = mutableListOf(it[0])
      }
    }
    return (findCase(depar, hub) * findCase(dest, hub)) % 10007
  }

  private fun findCase(depar: String, dest: String): Int {
    var count = 0
    val queue: Queue<String> = LinkedList<String>()
    queue.add(dest)

    while (queue.isNotEmpty()) {
      val pod = queue.poll()

      if (pod == depar) {
        count++
        continue
      }
      map[pod]?.forEach {
        queue.add(it)
      }
    }

    return count % 10007
  }
}