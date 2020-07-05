import java.util.Objects;

class Solution {
  public String solution(String p) {
    return separateBalancedString(p);
  }


  /**
   * 올바른 괄호 문자열을 체크하는 메서드
   * 여는괄호를 만나면 flag 변수 +1
   * 닫는괄호를 만나면 flag 변수 -1
   * 도중에 flag 변수가 음수가 되면 ( 닫는괄호가 더많아지면) false 를 리턴
   */
  private Boolean isCorrectString(String string) {
    int len = string.length();
    int flag = 0;

    for (int i = 0; i < len; i++) {
      if (Objects.equals(string.charAt(i), '(')) {
        flag++;
      } else {
        flag--;
      }
      if (flag < 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * 앞뒤 문자를 제거하고
   * 나머지 문자열의 괄호방향을 뒤집는 메서드
   */
  private String transformString(String string) {
    StringBuilder newString = new StringBuilder();

    for (int i = 0; i < string.length() - 2; i++) {
      if (Objects.equals(string.charAt(1 + i), '(')) {
        newString.append(')');
      } else {
        newString.append('(');
      }
    }
    return newString.toString();
  }

  private String separateBalancedString(String string) {

    if(isCorrectString(string)) {
      return  string;
    }

    int len = string.length();
    int flag = 0;
    int index = 0;

    for (int i = 0; i < len; i++) {
      if (Objects.equals(string.charAt(i), '(')) {
        flag++;
      } else {
        flag--;
      }
      if (flag == 0) {
        index = i + 1;
        break;
      }
    }
    String u = string.substring(0, index);
    String v = string.substring(index);

    if (isCorrectString(u)) {
      return u + separateBalancedString(v);
    }

    else {
      return '(' + separateBalancedString(v) + ')' + transformString(u);
    }
  }
}