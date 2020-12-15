import kotlin.math.max
import kotlin.math.min

class LeetCode1674 {

    fun minMoves(nums: IntArray, limit: Int): Int {
        val delta = IntArray(2*limit+2)
        for (i in 0 until nums.size/2) {
            val first = nums[i]
            val second = nums[nums.size -1 -i]
            delta[2] += 2
            delta[min(first, second)+1]--
            delta[first+second]--
            delta[first+second+1]++
            delta[max(first,second)+limit+1]++
        }
        var res = 2*nums.size
        var cur = 0
        for (i in 2..2*limit) {
            cur += delta[i]
            res = min(res, cur)
        }
        return res
    }
}