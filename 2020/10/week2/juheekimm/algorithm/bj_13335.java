import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] truck = new int[n];
        LinkedList<Integer> bridge = new LinkedList<Integer>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            truck[i] = Integer.parseInt(st.nextToken());

        int i = 0, time = 0, sum = 0;
        while (i < n) {
            if (!bridge.isEmpty() && bridge.size() >= w)
                sum -= bridge.removeFirst();

            if (sum + truck[i] <= L) {
                bridge.addLast(truck[i]);
                sum += truck[i++];
            } else {
                bridge.addLast(0);
            }
            time++;
        }
        System.out.println(time + w);
    }
}