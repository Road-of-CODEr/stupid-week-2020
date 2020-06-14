import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

  public int[] solution(int N, int[] stages) {

    int[] answer = new int[N];
    int[] countAry = new int[N + 2];
    int[] failAry = new int[N + 2];
    List<Stage> stageList = new ArrayList<>();

    for (int stage : stages) {
      failAry[stage]++;
      for (int i = 1; i <= stage; i++) {
        countAry[i]++;
      }
    }

    for (int i = 1; i <= N; i++) {
      if(countAry[i]==0) stageList.add(new Stage(i,0));
      else {
        stageList.add(new Stage(i, failAry[i] / (double) countAry[i]));
      }
    }
    Collections.sort(stageList);

    for (int i = 0; i < N; i++) {
      answer[i] = stageList.get(i).getIndex();
    }

    return answer;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();

    int[] testAry = {4, 4, 4, 4, 4};
    int[] resAry =  solution.solution(4, testAry);
    System.out.println();
  }
}

class Stage implements Comparable<Stage> {
  private int index;
  private double failure;

  Stage(int index, double failure) {
    this.index = index;
    this.failure = failure;
  }

  @Override
  public int compareTo(Stage stage) {
    if (this.failure == stage.failure) {
      if (this.index > stage.index) return 1;
    } else {
      if (stage.failure > this.failure) return 1;
    }
    return -1;
  }

  public int getIndex() {
    return index;
  }
}