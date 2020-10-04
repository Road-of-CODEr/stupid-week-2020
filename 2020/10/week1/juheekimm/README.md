# 10월 첫주차

## 알고리즘 3문제

#### 1. 백준 2733.Brainf*ck
``` java
import java.io.*;
import java.util.*;

public class Main {

	static int pointer, arr[] = new int[32768];
	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	static Stack<Integer> stack = new Stack<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb1, sb2;

		sb2 = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb1 = new StringBuilder();
			Arrays.fill(arr, 0);
			pointer = 0;
			map.clear();
			stack.clear();

			boolean exitFlag = false;
			int idx = 0;
			while (true) {
				String str = br.readLine();
				if (str.equals("end"))
					break;

				char[] temp = str.toCharArray();
				for (int i = 0, len = temp.length; i < len; i++) {
					if (temp[i] == '%') {
						break;
					} else if (temp[i] == '<' || temp[i] == '>' || temp[i] == '+' || temp[i] == '-' || temp[i] == '.'
							|| temp[i] == '[' || temp[i] == ']') {
						sb1.append(temp[i]);
						
						if (temp[i] == '[') {
							stack.push(idx);
						} else if (temp[i] == ']') {
							if (stack.size() > 0) {
								int left = stack.pop();
								map.put(left, idx);
								map.put(idx, left);
							} else {
								exitFlag = true;
								break;
							}
						}
						idx++;
					}
				}
			}
			sb2.append("PROGRAM #" + t + ":\n");
			if (exitFlag || stack.size() > 0)
				sb2.append("COMPILE ERROR\n");
			else
				sb2.append(brainFuck(sb1.toString()) + "\n");
		}
		System.out.println(sb2);
	}

	private static String brainFuck(String str) {
		StringBuilder sb = new StringBuilder();
		char[] temp = str.toCharArray();
		int idx = 0;
		
		while (true) {
			if (idx == str.length()) break;
			
			switch (temp[idx]) {
			case '<':
				if (pointer-- == 0)
					pointer = 32767;
				break;
			case '>':
				if (pointer++ == 32767)
					pointer = 0;
				break;
			case '+':
				if (arr[pointer]++ == 255)
					arr[pointer] = 0;
				break;
			case '-':
				if (arr[pointer]-- == 0)
					arr[pointer] = 255;
				break;
			case '.':
				sb.append((char) arr[pointer]);
				break;
			case '[':
				if (arr[pointer] == 0) {
					if (map.containsKey(idx))
						idx = map.get(idx);
					else
						idx = -1;
				}
				break;
			case ']':
				if (arr[pointer] != 0) {
					if (map.containsKey(idx))
						idx = map.get(idx);
					else
						idx = -1;
				}
				break;
			}
			
			if (idx == -1)
				return "COMPILE ERROR";
			else
				idx++;
		}
		return sb.toString();
	}
}
```

#### 2. 백준 16768. Mooyo Mooyo
``` java
import java.io.*;
import java.util.*;

public class Main {
	
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
```

#### 3. 백준 1966. 프린터 큐
``` java
import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static LinkedList<Integer> list = new LinkedList<Integer>();
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			list.clear();
			pq.clear();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int temp = Integer.parseInt(st.nextToken());
				list.add(temp);
				pq.add(temp);
			}
			sb.append(simulation() + "\n");
		}
		System.out.println(sb);
	}

	private static int simulation() {
		int count = 0;
		while (!list.isEmpty()) {
			if (pq.peek() > list.get(0)) {
				list.add(list.removeFirst());
				if (--M < 0) M = list.size() - 1;
			} else {
				pq.poll();
				list.removeFirst();
				count++;
				if (--M < 0) return count;
			}
		}
		return 0;
	}
}
```


