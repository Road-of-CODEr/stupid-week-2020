package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 12.                   
public class 레이저빔은어디로 {

	static int size;
	static boolean[][] map = new boolean[52][52];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int tnum = stoi(st.nextToken());
		for (int t = 1; t <= tnum; t++) {
			st = new StringTokenizer(br.readLine());
			size = stoi(st.nextToken());
			int M = stoi(st.nextToken());

			init();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				map[stoi(st.nextToken())][stoi(st.nextToken())] = true;
			}

			st = new StringTokenizer(br.readLine());
			sb.append(simulation(stoi(st.nextToken()), stoi(st.nextToken())));
		}
		System.out.print(sb.toString());
	}
	

	private static String simulation(int x, int y) {
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (isInner(nx, ny)) {
				return (move(nx, ny, map[nx][ny] ? (dir + 1) % 4 : dir) + "\n");
			}
		}
		return ""; // 나올 일 없음
	}

	private static String move(int x, int y, int dir) {
		while (isInner(x, y)) {
			x += dx[dir];
			y += dy[dir];
			if (map[x][y]) {
				dir = (dir + 1) % 4;
			}
		}
		return x + " " + y; // (0, 0) 나올 일 없음
	}


	private static void init() {
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				map[i][j] = false;
			}
		}
	}

	private static boolean isInner(int x, int y) {
		return x >= 1 && y >= 1 && x <= size && y <= size;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
