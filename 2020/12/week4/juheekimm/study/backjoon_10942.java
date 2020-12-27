import java.io.*;
import java.util.*;

public class backjoon_10942 {

    static int N, arr[], M;
    static boolean dp[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = stoi(br.readLine());
        arr = new int[N];
        dp = new boolean[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = stoi(st.nextToken());
        }
        palindrome();

        M = stoi(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = stoi(st.nextToken()) - 1;
            int e = stoi(st.nextToken()) - 1;
            sb.append((dp[s][e] ? "1" : "0") + "\n");
        }
        System.out.println(sb);
    }

    private static void palindrome() {
        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < N - 1; i++) {
            if (arr[i] == arr[i + 1])
                dp[i][i + 1] = true;
        }
        for (int i = 2; i <= N - 1; i++) {
            for (int j = 0; j < N - i; j++) {
                if (arr[j] == arr[j + i] && dp[j + 1][j + i - 1])
                    dp[j][j + i] = true;
            }
        }
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
}