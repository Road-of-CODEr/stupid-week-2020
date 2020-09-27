import java.io.*;
import java.util.*;

public class Main {

	static int r, c, size;
	static boolean[][] map;
	static boolean[][] visit;
	static boolean[][] check;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = stoi(st.nextToken());
		c = stoi(st.nextToken());

		map = new boolean[r + 2][c + 2];
		visit = new boolean[r + 2][c + 2];
		check = new boolean[r + 2][c + 2];

		for (int i = 1; i <= r; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 1; j <= c; j++) {
				if (input[j - 1] == 'x') {
					map[i][j] = true;
				}
			}
		}

		size = stoi(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < size; i++) {
			delete(r - stoi(st.nextToken()) + 1, i % 2 == 0 ? 1 : c);
		}
		print(map);
	}

	private static void delete(int num, int j) {
		int plus = -1;
		if (j == 1)
			plus = 1;

		while (j >= 1 && j <= c) {
			if (map[num][j]) {
				map[num][j] = false;
				isDivide(num, j);
				return;
			}
			j += plus;
		}
	}

	private static boolean isDivide(int x, int y) {
		init(visit);
		boolean flag = false;
		for (int j = 1; j <= c; j++) {
			if (map[r][j] && !visit[r][j]) {
				dfs(r, j);
			}
		}

		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if (map[i][j] && !visit[i][j]) {
					init(check);
					down(findMinLength(i, j));
					return true;
				}
			}
		}
		return false;
	}

	private static int findMinLength(int x, int y) {
		if (!map[x][y] || check[x][y]) {
			return Integer.MAX_VALUE;
		}
		check[x][y] = true;
		int min = r - x;

		for (int i = x; i <= r; i++) {
			if (visit[i][y]) {
				min -= r - i + 1;
				break;
			}
		}
//		System.out.println(x + "," + y + " = " + min);
		for (int i = 0; i < 4; i++) {
			min = Math.min(min, findMinLength(x + dx[i], y + dy[i]));
		}
		return min;
	}

	private static void dfs(int x, int y) {
		if (!map[x][y] || visit[x][y]) {
			return;
		}
		visit[x][y] = true;
		for (int i = 0; i < 4; i++) {
			dfs(x + dx[i], y + dy[i]);
		}
	}

	private static void down(int length) {
		for (int j = 1; j <= c; j++) {
			for (int i = r; i >= 1; i--) {
				if (map[i][j] && !visit[i][j]) {
					map[i][j] = false;
					map[i + length][j] = true;
				}
			}
		}
	}

	private static void init(boolean[][] visit) {
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				visit[i][j] = false;
			}
		}
	}

	private static void print(boolean[][] map) {
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				System.out.print(map[i][j] ? 'x' : '.');
			}
			System.out.println();
		}
		System.out.println();
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
