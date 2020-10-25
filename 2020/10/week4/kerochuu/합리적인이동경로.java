package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 10. 25.                   
public class 합리적인이동경로 {
	private static class Node implements Comparable<Node> {
		int idx, cost;

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	static int N, M;
	static int[] count, dist;
	static ArrayList<Node>[] map;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		init();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			input(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
		}

		System.out.println(dijkstra(2));
	}

	private static int dijkstra(int starting) {
		count[starting] = 1;
		dist[starting] = 0;
		pq.add(new Node(starting, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (now.cost > dist[now.idx]) {
				continue;
			}

			for (Node next : map[now.idx]) {
				if (dist[next.idx] > dist[now.idx] + next.cost) {
					dist[next.idx] = dist[now.idx] + next.cost;
					pq.add(new Node(next.idx, dist[next.idx]));
				}
				if (dist[next.idx] < now.cost) {
					count[now.idx] += count[next.idx];
				}
			}
		}
		return count[1];
	}

	private static void input(int a, int b, int cost) {
		map[a].add(new Node(b, cost));
		map[b].add(new Node(a, cost));
	}

	private static void init() {
		map = new ArrayList[N + 1];
		count = new int[N + 1];
		dist = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
			dist[i] = Integer.MAX_VALUE;
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
