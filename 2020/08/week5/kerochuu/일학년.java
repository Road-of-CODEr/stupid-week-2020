package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 30.                   
public class ¿œ«–≥‚ {

	static int N;
	static int[] input;
	static long[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		init();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = stoi(st.nextToken());
		}

		dp[0][input[0]] = 1;
		System.out.println(recursive(1));
	}

	private static long recursive(int depth) {
		if (depth == N - 1) {
			return dp[depth - 1][input[depth]];
		}
		for (int num = 0; num < 21; num++) {
			if (dp[depth - 1][num] != 0) {

				if (num + input[depth] <= 20) {
					dp[depth][num + input[depth]] += dp[depth - 1][num];
				}

				if (num - input[depth] >= 0) {
					dp[depth][num - input[depth]] += dp[depth - 1][num];
				}
			}
		}
		return recursive(depth + 1);
	}

	private static void init() {
		input = new int[N];
		dp = new long[N - 1][21];
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
