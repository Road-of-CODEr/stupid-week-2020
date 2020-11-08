import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AbusingMap {
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