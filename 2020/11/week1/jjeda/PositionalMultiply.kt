class PositionalMultiply {
  private var positionalMultiply = 0
  private var number = 0

  fun solution(N: Int): IntArray {
    for (i in 9 downTo 2) {
      val convertedNum = N.toString(i)
      val positionalMultiply = convertedNum.filter { it.toInt() > 0 }.multiplyOf()
      if (this.positionalMultiply < positionalMultiply) {
        this.positionalMultiply = positionalMultiply
        this.number = i
      }
    }
    return intArrayOf(this.number, this.positionalMultiply)
  }
  private fun String.multiplyOf(): Int {
    var accumulate = 1
    this.forEach { accumulate *= (it - 48).toInt() }
    return accumulate
  }
}