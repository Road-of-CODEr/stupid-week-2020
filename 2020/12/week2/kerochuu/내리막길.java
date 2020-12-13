package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 12. 12.                   
public class 내리막길 {

	static int R, C;
	static int[][] map;
	static int[][] dp;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		init();

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}

		System.out.println(dfs(0, 0));
	}

	private static int dfs(int x, int y) {
		if (x == R - 1 && y == C - 1) {
			return 1;
		}

		if (dp[x][y] != -1) {
			return dp[x][y];
		}

		dp[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < R && ny < C && map[x][y] > map[nx][ny]) {
				dp[x][y] += dfs(nx, ny);
			}
		}
		return dp[x][y];
	}

	private static void init() {
		map = new int[R][C];
		dp = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				dp[i][j] = -1;
			}
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
