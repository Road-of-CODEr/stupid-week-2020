import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr, combi;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visit = new boolean[N];
        combi = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        permu(M, 0);
        System.out.println(sb);
    }

    private static void permu(int n, int k) {
        if (n == k) {
            for (int i = 0; i < n; i++)
                sb.append(arr[combi[i]] + " ");
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visit[i]) continue;
            combi[k] = i;
            visit[i] = true;
            permu(n, k + 1);
            visit[i] = false;
        }
    }
}