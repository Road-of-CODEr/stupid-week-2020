import java.util.Arrays;
import java.util.Collections;

public class Kiosk {
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