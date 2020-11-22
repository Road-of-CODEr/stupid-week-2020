package baekjoon;

import java.io.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 22.                   
public class LCS {

	static int[][] dp;
	static char[] word1, word2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		word1 = br.readLine().toCharArray();
		word2 = br.readLine().toCharArray();
		
		System.out.println(createDP(word1.length, word2.length));
	}

	private static int createDP(int N, int M) {
		dp = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[i][j] = (word1[i - 1] == word2[j - 1]) ? dp[i - 1][j - 1] + 1 : Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp[N][M];
	}
}
