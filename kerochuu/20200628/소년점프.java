package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 6. 28.                   
public class 소년점프 {

	private static class Node {
		int x, y, step;

		Node(int x, int y, int step) {
			this.x = x;
			this.y = y;
			this.step = step;
		}
	}

	static int R, C;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] map;
	static int[][][] visit;
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = stoi(st.nextToken());
		C = stoi(st.nextToken());

		init();

		for (int i = 0; i < R; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp[j] == '0' ? true : false;
				for (int k = 0; k < 3; k++) {
					visit[i][j][k] = -1;
				}
			}
		}

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			bfs(stoi(st.nextToken()) - 1, stoi(st.nextToken()) - 1, i);
		}

		System.out.println(count());
	}

	private static void bfs(int x, int y, int k) {
		q.add(new Node(x, y, 0));
		visit[x][y][k] = 0;
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];

				if (nx >= 0 && ny >= 0 && nx < R && ny < C && map[nx][ny] && visit[nx][ny][k] == -1) {
					visit[nx][ny][k] = n.step + 1;
					q.add(new Node(nx, ny, n.step + 1));
				}
			}
		}
	}

	private static String count() {
		int count = -1;
		int max = Integer.MAX_VALUE;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (visit[i][j][0] != -1 && visit[i][j][1] != -1 && visit[i][j][2] != -1) {
					int min = Math.max(visit[i][j][0], Math.max(visit[i][j][1], visit[i][j][2]));

					if (max > min) {
						max = min;
						count = 1;
					} else if (max == min) {
						count++;
					}
				}
			}
		}
		return count == -1 ? "-1" : (max + " " + count);
	}

	private static void init() {
		map = new boolean[R][C];
		visit = new int[R][C][3];
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
