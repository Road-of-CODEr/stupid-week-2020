class FunctionDevelopment {

  public int[] solution(int[] progresses, int[] speeds) {
    int length = progresses.length;
    int[] progressDay = new int[length];
    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < length; i++) {
      progressDay[i] = (100 - progresses[i]) % speeds[i] == 0 ?
          (100 - progresses[i]) / speeds[i] : (100 - progresses[i]) / speeds[i] + 1;
    }

    int pivotIndex = 0;
    int cnt = 0;
    for (int i = 0; i < length; i++) {

      if (progressDay[i] > progressDay[pivotIndex]) {
        list.add(cnt);
        cnt = 1;
        pivotIndex = i;
      } else {
        cnt++;
      }
    }
    list.add(cnt);

    return list.stream().mapToInt(i -> i).toArray();
  }
}