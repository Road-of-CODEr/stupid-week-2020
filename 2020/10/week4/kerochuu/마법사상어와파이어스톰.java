package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 10. 25.                   
public class 마법사상어와파이어스톰 {

	static int size, N;
	static int[][] map = new int[66][66];
	static int[][] tempMap = new int[66][66];
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][] visit = new boolean[66][66];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		size = 1 << stoi(st.nextToken());
		N = stoi(st.nextToken());

		for (int i = 1; i <= size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= size; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			simulation(stoi(st.nextToken()));
		}

		System.out.println(calc());
	}

	private static void simulation(int level) {
		casting(1, size, 1, size, size, 1 << level);
		melt();
	}

	private static void casting(int sx, int ex, int sy, int ey, int nowSize, int targetSize) {

		if (nowSize == targetSize) {
			rotate(sx, ex, sy, ey, nowSize >> 1);
		} else {
			nowSize >>= 1; // 0 8 0 8 4
			casting(sx, ex - nowSize, sy, ey - nowSize, nowSize, targetSize); // 1 4 1 4
			casting(sx + nowSize, ex, sy, ey - nowSize, nowSize, targetSize); // 5 8 1 4
			casting(sx, ex - nowSize, sy + nowSize, ey, nowSize, targetSize); // 1 4 5 8
			casting(sx + nowSize, ex, sy + nowSize, ey, nowSize, targetSize); // 5 8 5 8
		}
	}

	private static void rotate(int sx, int ex, int sy, int ey, int count) {
		int x, y, dir, temp, nx = -1, ny = -1;
		for (int c = 0; c < count; c++) {
			for (int t = 1; t < (count - c) << 1; t++) {
				x = sx + c;
				y = sy + c;
				dir = 0;
				temp = map[x][y];
				while (dir != 4) {
					nx = x + dx[dir];
					ny = y + dy[dir];
					if (nx >= sx + c && ny >= sy + c && nx <= ex - c && ny <= ey - c) {
						map[x][y] = map[nx][ny];
						x = nx;
						y = ny;
					} else {
						dir++;
					}
				}
				map[x][y + 1] = temp;

			}
		}
	}

	private static void melt() {
		tempMapInit();
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				if (map[i][j] != 0) {
					int count = 0;
					for (int d = 0; d < 4; d++) {
						if (map[i + dx[d]][j + dy[d]] > 0) {
							count++;
						}
					}
					if (count < 3) {
						tempMap[i][j]--;
					}
				}
			}
		}

		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				if (tempMap[i][j] != 0) {
					map[i][j] += tempMap[i][j];
				}
			}
		}
	}

	private static String calc() {
		int allSum = 0, max = 0;
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				if (!visit[i][j] && map[i][j] > 0) {
					visit[i][j] = true;
					max = Math.max(max, dfs(i, j));
				}
				allSum += map[i][j];
			}
		}
		return allSum + "\n" + max;
	}

	private static int dfs(int x, int y) {
		int allSum = 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!visit[nx][ny] && map[nx][ny] > 0) {
				visit[nx][ny] = true;
				allSum += dfs(nx, ny);
			}
		}
		return allSum;
	}

	private static void tempMapInit() {
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				tempMap[i][j] = 0;
			}
		}
	}

	private static void print() {
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				System.out.format("%2d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
