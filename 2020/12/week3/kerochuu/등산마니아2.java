package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 12. 19.                   
public class 등산마니아2 {

	static int N;
	static long result = 0;
	static int[] dp;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());

		init();

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}

		createDP(1);
		System.out.println(result);
	}

	private static int createDP(int now) {
		dp[now] = 1;
		for (int next : list[now]) {
			if (dp[next] == 0) {
				dp[now] += createDP(next);
			}
		}
		if (now != 1) {
			result += comb(N) - comb(N - dp[now]);
		}
		return dp[now];
	}

	private static long comb(int n) {
		return (long) n * (long) (n - 1) / 2;
	}

	private static void init() {
		list = new ArrayList[N + 1];
		dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
