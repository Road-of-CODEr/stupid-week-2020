class Solution {
  fun solution(p: String): String {
    return separateBalancedString(p)
  }

  private fun isCorrectString(string: String): Boolean {
    val len = string.length
    var flag = 0
    for (i in 0 until len) {
      if (string[i] == '(') {
        flag++
      } else {
        flag--
      }
      if (flag < 0) {
        return false
      }
    }
    return true
  }

  private fun transformString(string: String): String {
    val newString = StringBuilder()
    for (i in 0 until string.length - 2) {
      if (string[1 + i] == '(') {
        newString.append(')')
      } else {
        newString.append('(')
      }
    }
    return newString.toString()
  }

  private fun separateBalancedString(string: String): String {
    if (isCorrectString(string)) {
      return string
    }
    val len = string.length
    var flag = 0
    var index = 0
    for (i in 0 until len) {
      if (string[i] == '(') {
        flag++
      } else {
        flag--
      }
      if (flag == 0) {
        index = i + 1
        break
      }
    }
    val u = string.substring(0, index)
    val v = string.substring(index)
    return if (isCorrectString(u)) {
      u + separateBalancedString(v)
    } else {
      '('.toString() + separateBalancedString(v) + ')' + transformString(u)
    }
  }
}