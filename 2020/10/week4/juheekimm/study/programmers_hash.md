[프로그래머스 알고리즘 문제 목록](https://programmers.co.kr/learn/challenges?tab=algorithm_practice_kit)
##### 완주하지 못한 선수

```java
import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0, size = completion.length; i < size; i++) {
            if (map.containsKey(completion[i]))
                map.put(completion[i], map.get(completion[i]) + 1);
            else
                map.put(completion[i], 1);
        }
            
        
        for (int i = 0, size = participant.length; i < size; i++) {
            if (map.containsKey(participant[i])) {
                if (map.get(participant[i]) == 1)
                    map.remove(participant[i]);
                else
                    map.put(participant[i], map.get(participant[i]) - 1);
            } else
                return participant[i];
        }
        return "";
    }
}
 ```

##### 전화번호 목록
```java
import java.util.*;
       
       class Solution {
           public boolean solution(String[] phone_book) {
               StringBuilder sb;
               
               boolean answer = true;
               for (int i = 0; i < phone_book.length; i++) {
                   phone_book[i] = phone_book[i].replace(" ", "");
               }
               Arrays.sort(phone_book, new Comparator<String>() {
                          @Override
                          public int compare(String s1, String s2) {
                              return s1.length() - s2.length();
                          }
                   });
               HashSet<String> set = new HashSet<String>();
               
               for (int i = 0; i < phone_book.length; i++) {
                   sb = new StringBuilder();
                   for (int j = 0; j < phone_book[i].length(); j++) {
                       sb.append(phone_book[i].charAt(j));
                       if (set.contains(sb.toString())) {
                           return false;
                       }
                   }
                   set.add(phone_book[i]);
               }
               return true;
           }
       }
 ```