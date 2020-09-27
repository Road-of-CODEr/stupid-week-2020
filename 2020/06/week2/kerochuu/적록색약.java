package baekjoon;

import java.io.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 6. 14.                   
public class 적록색약 {

	static int N;
	static char[][] map;
	static boolean[][] visit;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		for (int i = 0; i < N; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = input[j];
			}
		}

		System.out.println(findArea(false) + " " + findArea(true));
	}

	private static int findArea(boolean isColorWeak) {
		int count = 0;
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					if (isColorWeak && (map[i][j] == 'R' || map[i][j] == 'G')) {
						dfs(i, j, "RG");
					} else {
						dfs(i, j, String.valueOf(map[i][j]));
					}
					count++;
				}
			}
		}
		return count;
	}

	private static void dfs(int x, int y, String type) {
		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visit[nx][ny]) {
				for (int t = 0; t < type.length(); t++) {
					if (type.charAt(t) == map[nx][ny]) {
						dfs(nx, ny, type);
					}
				}
			}
		}
	}
}
