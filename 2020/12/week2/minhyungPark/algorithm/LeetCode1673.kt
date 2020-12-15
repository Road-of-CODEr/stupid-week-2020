import java.util.*

class LeetCode1673 {
    fun mostCompetitive(nums: IntArray, k: Int): IntArray {
        val stack = Stack<Int>()
        for ((i,n) in nums.withIndex()) {
            while (stack.isNotEmpty()
                && n < stack.peek()
                && stack.size + nums.size - i > k) {
                stack.pop()
            }
            if (stack.size < k) {
                stack.push(n)
            }
        }
        return stack.toIntArray()
    }
}