package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 14.                   
public class 인성문제있어 {
	private static class Node {
		int x, y, power;

		Node(int x, int y, int power) {
			this.x = x;
			this.y = y;
			this.power = power;
		}
	}

	static int R, C, O, F, X, Y;
	static int[][] map = new int[101][101];
	static boolean[][] visit = new boolean[101][101];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Node> q = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = stoi(st.nextToken());

		for (int t = 1; t <= T; t++) {
			init(new StringTokenizer(br.readLine()));
			for (int i = 0; i < O; i++) {
				st = new StringTokenizer(br.readLine());
				map[stoi(st.nextToken())][stoi(st.nextToken())] = stoi(st.nextToken());
			}
			sb.append(bfs() + "\n");
		}
		System.out.print(sb.toString());
	}

	private static String bfs() {
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = n.x + dx[i];
				int ny = n.y + dy[i];
				if (nx >= 1 && ny >= 1 && nx <= R && ny <= C && !visit[nx][ny]) {
					if (map[nx][ny] - map[n.x][n.y] <= n.power) {
						visit[nx][ny] = true;
						if(nx == X && ny == Y) {
							return "잘했어!!";
						}
						if (n.power >= 1) {
							q.add(new Node(nx, ny, n.power - 1));
						}
					}
				}
			}
		}
		return "인성 문제있어??";
	}

	private static void init(StringTokenizer st) {
		q.clear();
		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				map[i][j] = 0;
				visit[i][j] = false;
			}
		}
		O = stoi(st.nextToken());
		F = stoi(st.nextToken());
		q.add(new Node(stoi(st.nextToken()), stoi(st.nextToken()), F));
		visit[q.peek().x][q.peek().y] = true;

		X = stoi(st.nextToken());
		Y = stoi(st.nextToken());
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
