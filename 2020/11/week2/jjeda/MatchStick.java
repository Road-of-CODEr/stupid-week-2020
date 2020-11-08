public class MatchStick {

  private static int[] cost = { 6, 2, 5, 5, 4, 5, 6, 3, 7, 6 };
  private static long count;

  private long solution(int k) {
    count = 0;
    process(k, 0, -1);
    return count;
  }

  private void process(int num, int depth, int before) {
    if (num == 0) {
      count++;
      return;
    }
    if (before == 0 && depth == 1) {
      return;
    }

    for (int i = 0; i < 10; i++) {
      if (num - cost[i] >= 0) {
        process(num - cost[i], depth + 1, i);
      }
    }
  }
}