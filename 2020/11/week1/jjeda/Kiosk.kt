class Kiosk {
  fun solution(n: Int, customers: Array<String>): Int {
    val kiosks = IntArray(n) { 0 }
    val counts = IntArray(n) { 0 }

    customers.forEach { customerInfo ->
      val (customTime, processTime) = customerInfoToCustomTime(customerInfo)
      var maxDiff = Int.MIN_VALUE
      var maxIndex = -1

      for ((index, value) in kiosks.withIndex()) {
        val diff = customTime - value

        if (maxDiff < diff) {
          maxDiff = diff
          maxIndex = index
        }
      }
      kiosks[maxIndex] = if ( maxDiff >= 0 ) {
        customTime + processTime
      } else {
        customTime + processTime - maxDiff
      }
      counts[maxIndex]++
    }
    return counts.max() ?: 0
  }

  private fun customerInfoToCustomTime(customerInfo: String): Pair<Int, Int> {
    val (mmdd, time, processTime) = customerInfo.split(' ')
    val (month, day) = mmdd.split('/')
    val (hour, min, sec) = time.split(':')
    val dateToCustomInt = (month.toInt() * 31 + day.toInt()) * 86400 + (hour.toInt() * 3600 + min.toInt() * 60 + sec
        .toInt())
    return dateToCustomInt to processTime.toInt() * 60
  }
}