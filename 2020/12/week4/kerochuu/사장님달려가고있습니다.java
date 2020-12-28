package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 12. 27.                   
public class 사장님달려가고있습니다 {
	private static class Node {
		int x, y, accel, dir;

		Node(int x, int y, int accel, int dir) {
			this.x = x;
			this.y = y;
			this.accel = accel;
			this.dir = dir;
		}
	}

	static int N;
	static int[][] map;
	static int[][][][] dist;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static Queue<Node> q = new LinkedList<Node>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		init();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stoi(st.nextToken());
				if (map[i][j] == 0) {
					map[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		if (map[0][1] > 1) {
			q.add(new Node(0, 1, 1, 2));
			dist[0][1][1][2] = 1;
		}
		if (map[1][0] > 1) {
			q.add(new Node(1, 0, 1, 0));
			dist[1][0][1][0] = 1;
		}

		System.out.println(bfs());
	}

	private static String bfs() {
		int nx, ny, now;
		while (!q.isEmpty()) {
			Node n = q.poll();
			now = dist[n.x][n.y][n.accel][n.dir];
			if (n.x == N - 1 && n.y == N - 1) {
				return now + "";
			}

			for (int i = 0; i < 4; i++) {
				int weight = n.dir == i ? n.accel + 1 : 1;
				nx = n.x + dx[i] * weight;
				ny = n.y + dy[i] * weight;
				if (nx >= 0 && ny >= 0 && nx < N && ny < N && isPossible(n, nx, ny, i, now)) {
					dist[nx][ny][weight][i] = now + 1;
					q.add(new Node(nx, ny, weight, i));
				}
			}
		}
		return "Fired";
	}

	private static boolean isPossible(Node n, int nx, int ny, int dir, int now) {
		if (dist[nx][ny][n.accel][dir] == -1 && map[nx][ny] >= now) {
			for (int tx = n.x, ty = n.y; tx != nx || ty != ny; tx += dx[dir], ty += dy[dir]) {
				if (map[tx][ty] <= now) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	private static void init() {
		map = new int[N][N];
		dist = new int[N][N][N][4];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					for (int l = 0; l < 4; l++) {
						if (i == 0 && j == 0 && k == 0) continue;
						dist[i][j][k][l] = -1;
					}
				}
			}
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
