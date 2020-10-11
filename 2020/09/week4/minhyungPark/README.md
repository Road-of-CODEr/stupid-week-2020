## Objective - 4th Sep

1. [leetcode](#algo1)
2. [leetcode](#algo2)
3. [k8s 기초다지기 7장 정리](./k8s.md)

---

####  <a name="algo1"></a> [Maximum Profit of Operating a Centennial Wheel](https://leetcode.com/problems/maximum-profit-of-operating-a-centennial-wheel/)

```kotlin
class Solution {
    companion object {
        const val MAX_BOARDING_CUSTOMERS = 4
    }

    fun minOperationsMaxProfit(customers: IntArray, boardingCost: Int, runningCost: Int): Int {
        val size = customers.size
        var index = 0
        var waitingCustomers = customers[0]
        var boardingCustomers = 0
        var maxProfit = 0
        var maxProfitIndex = -1

        while (index++ < size || waitingCustomers > 0) {
            val firstSectionCustomers = takeCustomers(waitingCustomers)
            waitingCustomers -= firstSectionCustomers
            boardingCustomers += firstSectionCustomers

            val profit = boardingCustomers * boardingCost - runningCost * index
            if (profit > maxProfit) {
                maxProfit = profit
                maxProfitIndex = index
            }
            if (index < size) {
                waitingCustomers += customers[index]
            }
        }
        return maxProfitIndex
    }

    private fun takeCustomers(waitingCustomers: Int): Int {
        return if (waitingCustomers <= MAX_BOARDING_CUSTOMERS) {
            waitingCustomers
        } else {
            MAX_BOARDING_CUSTOMERS
        }
    }
}
```



####  <a name="algo2"></a>[Throne Inheritance](https://leetcode.com/problems/throne-inheritance/)

```kotlin
class ThroneInheritance(private val kingName: String) {
    private val inheritance = mutableMapOf<String, MutableList<String>>(kingName to mutableListOf())
    private val deathPeople = mutableSetOf<String>()

    fun birth(parentName: String, childName: String) {
        inheritance[parentName]!!.add(childName)
        inheritance[childName] = mutableListOf()
    }

    fun death(name: String) {
        deathPeople.add(name)
    }

    fun getInheritanceOrder(): List<String> {
        val inheritanceOrder = mutableListOf<String>()
        makeInheritanceOrder(kingName, inheritanceOrder)
        return inheritanceOrder
    }
    
    private fun makeInheritanceOrder(name: String, inheritanceOrder: MutableList<String>) {
        if (!deathPeople.contains(name)) {
            inheritanceOrder.add(name)
        }
        inheritance[name]!!.forEach { makeInheritanceOrder(it, inheritanceOrder) }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * var obj = ThroneInheritance(kingName)
 * obj.birth(parentName,childName)
 * obj.death(name)
 * var param_3 = obj.getInheritanceOrder()
 */
```