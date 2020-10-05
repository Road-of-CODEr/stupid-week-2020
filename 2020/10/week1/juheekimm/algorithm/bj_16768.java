import java.io.*;
import java.util.*;

public class bj_16768 {
	
	static class Node {
		int x, y, step, val;
		Node (int x, int y, int step, int val) {
			this.x = x;
			this.y = y;
			this.step = step;
			this.val = val;
		}
	}
	
	static int N, K, count, map[][];
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
	static boolean[][] visit, bombVisit;
	static Queue<Node> bombQ = new LinkedList<>();
	static Queue<Node> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][10];
		visit = new boolean[N][10];
		bombVisit = new boolean[N][10];
		
		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < 10; j++) {
				map[i][j] = temp[j] - '0';
			}
		}
		
		while (true) {
			findStart();
			if (bombQ.isEmpty()) break;
			
			bomb();
			fall();
			for (int i = 0; i < N; i++) {
				Arrays.fill(visit[i], false);
				Arrays.fill(bombVisit[i], false);
			}
			q.clear();
			bombQ.clear();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 10; j++)
				sb.append(map[i][j]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void fall() {
		for (int j = 0; j < 10; j++) {
			for (int i = N - 1; i >= 0; i--) {
				if (map[i][j] == 0) {
					int prev = i;
					while (i > 0 && map[--i][j] == 0);
					if (i < 0) continue;
					
					map[prev][j] = map[i][j];
					map[i][j] = 0;
					i = prev;
				}
			}
		}
	}

	private static void bomb() {
		while (!bombQ.isEmpty()) {
			Node temp = bombQ.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= 10
						|| bombVisit[nx][ny] || map[nx][ny] != temp.val)
					continue;
				
				bombVisit[nx][ny] = true;
				map[nx][ny] = 0;
				bombQ.add(new Node(nx, ny, temp.step + 1, temp.val));
			}
		}
	}

	private static void findStart() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] != 0 && !visit[i][j]) {
					visit[i][j] = true;
					q.add(new Node(i, j, 1, map[i][j]));
					count = 1;
					if (bfs()) {
						bombQ.add(new Node(i, j, 1, map[i][j]));
						bombVisit[i][j] = true;
						map[i][j] = 0;
					}
				}
			}
		}
	}

	private static boolean bfs() {
		boolean enableBomb = false;
		while (!q.isEmpty()) {
			Node temp = q.poll();
			if (count >= K)
				enableBomb = true;
			
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= 10
						|| visit[nx][ny] || map[nx][ny] != temp.val)
					continue;
				
				visit[nx][ny] = true;
				q.add(new Node(nx, ny, temp.step + 1, temp.val));
				count++;
			}
		}
		return enableBomb;
	}
}