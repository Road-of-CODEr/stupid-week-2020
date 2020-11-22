class LeetCode1657 {

    fun closeStrings(word1: String, word2: String): Boolean {
        if (word1.length != word2.length) {
            return false
        }

        val arr1 = IntArray(26)
        val arr2 = IntArray(26)
        val set1 = mutableSetOf<Char>()
        val set2 = mutableSetOf<Char>()

        for (i in word1.indices) {
            arr1[word1[i] - 'a']++
            arr2[word2[i] - 'a']++
        }

        word1.toCollection(set1)
        word2.toCollection(set2)


        val list1 = arr1.filter { it != 0 }.toList().sorted()
        val list2 = arr2.filter { it != 0 }.toList().sorted()

        return list1 == list2 && set1 == set2
    }

}