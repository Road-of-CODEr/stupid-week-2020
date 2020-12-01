package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 28.                   
public class 좀비 {
	private static class Node implements Comparable<Node> {
		int idx;
		long cost;

		Node(int idx, long cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost < o.cost ? -1 : 1;
		}
	}

	static int N, M, K, S, safe, unsafe;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static ArrayList<Integer>[] map;
	static boolean[] visit, zombie, unSafe;
	static Queue<Integer> zombieQ = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());
		S = stoi(st.nextToken());

		init();

		st = new StringTokenizer(br.readLine());
		safe = stoi(st.nextToken());
		unsafe = stoi(st.nextToken());

		int idx;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			idx = stoi(st.nextToken());
			zombieQ.add(idx);
			zombie[idx] = true;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			input(stoi(st.nextToken()), stoi(st.nextToken()));
		}

		calcSafeTown();
		System.out.println(dijkstra());
	}

	private static long dijkstra() {
		pq.add(new Node(1, 0));
		visit[1] = true;
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			for (int next : map[n.idx]) {
				if (next == N) {
					return n.cost;
				}
				if (!zombie[next]) {
					if (!visit[next]) {
						visit[next] = true;
						pq.add(new Node(next, n.cost + (unSafe[next] ? unsafe : safe)));
					}
				}
			}
		}
		return -1;
	}

	private static void calcSafeTown() {
		for (int i = 0; i <= S; i++) {
			if (zombieQ.isEmpty()) break;

			for (int j = zombieQ.size() - 1; j >= 0; j--) {
				int now = zombieQ.poll();
				if (!unSafe[now]) {
					unSafe[now] = true;
					for (int next : map[now]) {
						zombieQ.add(next);
					}
				}
			}
		}
	}

	private static void input(int a, int b) {
		map[a].add(b);
		map[b].add(a);
	}

	private static void init() {
		map = new ArrayList[N + 1];
		zombie = new boolean[N + 1];
		visit = new boolean[N + 1];
		unSafe = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
