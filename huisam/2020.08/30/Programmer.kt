fun solution(progresses: IntArray, speeds: IntArray): IntArray {
    val answer = LinkedList<Int>()
    val queue = LinkedList<Int>()
    for (i in progresses.indices) {
        val remain = 100 - progresses.get(i)
        val day = remain / speeds.get(i)

        when {
            remain % speeds.get(i) == 0 -> {
                queue.add(day)
            }
            else -> {
                queue.add(day + 1)
            }
        }
    }

    while (!queue.isEmpty()) {
        val front = queue.pop()
        var count = 1
        while (!queue.isEmpty() && front > queue.first) {
            count++
            queue.poll()
        }
        answer.add(count)
    }
    return answer.toIntArray()
}