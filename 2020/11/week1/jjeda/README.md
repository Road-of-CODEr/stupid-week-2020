## 알고리즘 풀기 with Java

- 코딩테스트 1번
```kotlin
class Solution {
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
```

```java
/* Java 버전 */
public class Solution {
  private int positionalMultiply = 0;
  private int number = 0;


  private int[] solution(int N) {
    for (int i = 9; i > 0; i -= 2) {
      String convertedNum = Integer.toString(N, i);
      int positionalMultiply = multiplyOfString(convertedNum.chars().filter (c -> c > 0).toString());

      if (this.positionalMultiply < positionalMultiply) {
        this.positionalMultiply = positionalMultiply;
        this.number = i;
      }
    }
    int[] intArray = new int[2];
    intArray[0] = this.number;
    intArray[1] = this.positionalMultiply;
    return  intArray;
  }

  private int multiplyOfString(String target) {
    int accumulate = 1;
    for (char character : target.toCharArray()) {
      accumulate *= (character - 48);
    }
    return accumulate;
  }
}
```


- 코딩테스트 2번
```kotlin
class Solution {
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
```

```java
/* Java 버전*/
import java.util.Arrays;
import java.util.Collections;

public class Solution {
  int solution(int n, String[] customers) {
    int[] kiosks = new int[n];
    Integer[] counts = new Integer[n];

    Arrays.stream(customers).forEach(customerInfo -> {
      int[] results = customerInfoToCustomTime(customerInfo);
      int customTime = results[0];
      int processTime = results[1];

      int maxDiff = Integer.MAX_VALUE;
      int maxIndex = -1;

      for (int i = 0; i < n; i++) {
        int diff = customTime - kiosks[i];

        if (maxDiff < diff) {
          maxDiff = diff;
          maxIndex = i;
        }
      }
      if (maxDiff >= 0) {
        kiosks[maxIndex] = customTime + processTime;
      } else {
        kiosks[maxIndex] = customTime + processTime - maxDiff;
      }
      counts[maxIndex]++;
    });
    return Collections.max(Arrays.asList(counts));
  }

  private int[] customerInfoToCustomTime(String customerInfo) {
    String[] splitCustomerInfo = customerInfo.split(" ");
    String[] mmdd = splitCustomerInfo[0].split("/");
    String[] time = splitCustomerInfo[1].split(":");
    String processTime = splitCustomerInfo[2];

    String month = mmdd[0];
    String day = mmdd[1];
    String hour = time[0];
    String min = time[1];
    String sec = time[2];

    int dateToCustomInt = (Integer.parseInt(month) * 31 + Integer.parseInt(day)) * 86400 +
        (Integer.parseInt(hour) * 3600 + Integer.parseInt(min) * 60 + Integer.parseInt(sec));

    return new int[]{dateToCustomInt, Integer.parseInt(processTime) * 60};
  }
}
```

- 코딩테스트 3번
```kotlin
class Solution3 {
  fun solution(k: Int, score: IntArray): Int {
    val map = mutableMapOf<Int, Int>()
    val check = Array(score.size) { true }

    for ((index, value) in score.withIndex()) {
      if (index == 0) continue

      val diff = score[index - 1] - value
      map[diff] = map[diff]?.plus(1) ?: 1
    }
    val abusingMap = map.filterValues { it >= k }

    for ((index, value) in score.withIndex()) {
      if (index == 0) continue

      val diff = score[index - 1] - value
      if (abusingMap.containsKey(diff)) {
        check[index - 1] = false
        check[index] = false
      }
    }


    return check.count { it }
  }
}
```

```java
/* Java 버전*/
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
  int solution(int k, int[] score) {
    Map<Integer, Integer> map = new HashMap<>();
    Boolean[] check = new Boolean[score.length];
    Arrays.fill(check, true);

    for (int i = 0; i < score.length; i++) {
      if (i == 0) continue;

      int diff = score[i - 1] = score[i];
      map.put(diff, map.get(diff) + 1);
    }

    Map<Integer, Integer> abusingMap = map.entrySet().stream()
        .filter(entry -> entry.getKey() >= k)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    for (int i = 0; i < score.length; i++) {
      if (i == 0) continue;

      int diff = score[i - 1] = score[i];
      if (abusingMap.containsKey(diff)) {
        check[i - 1] = false;
        check[i] = false;
      }
    }
    return (int) Arrays.stream(check).filter(it -> it).count();
  }
}
```