public class PositionalMultiply {
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