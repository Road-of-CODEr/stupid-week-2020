package leetcode

class LeetCode1678 {

    companion object {
        val commandToValueMap = mapOf(
            "G" to "G",
            "()" to "o",
            "(al)" to "al"
        )
    }
    fun interpret(command: String): String {
        var result = command
        commandToValueMap.entries.forEach {
            result = result.replace(it.key, it.value)
        }
        return result
    }
}