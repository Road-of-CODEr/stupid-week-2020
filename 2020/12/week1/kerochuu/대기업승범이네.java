package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 12. 6.                   
public class 대기업승범이네 {

	static int N;
	static int[] value;
	static int[][] dp;
	static ArrayList<Integer>[] children;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		init();

		st = new StringTokenizer(br.readLine());
		for (int i = 2; i <= N; i++) {
			children[stoi(st.nextToken())].add(i);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			value[i] = stoi(st.nextToken());
		}

		System.out.println(createDP(1, 0));
	}

	private static int createDP(int now, int pick) {

		if (dp[now][pick] != -1) {
			return dp[now][pick];
		}

		dp[now][pick] = 0;
		if (pick == 0) {
			int allSum = 0, max = 0;
			for (int son : children[now]) {
				allSum += createDP(son, 0);
			}

			for (int son : children[now]) {
				max = Math.max(max, value[now] * value[son] - createDP(son, 0) + createDP(son, 1));
			}
			dp[now][pick] = Math.max(dp[now][pick], allSum + max);
		} else {
			for (int son : children[now]) {
				dp[now][pick] += createDP(son, 0);
			}
		}
		return dp[now][pick];
	}

	private static void init() {
		value = new int[N + 1];
		dp = new int[N + 1][2];
		children = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			children[i] = new ArrayList<>();
			dp[i][0] = dp[i][1] = -1;
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
