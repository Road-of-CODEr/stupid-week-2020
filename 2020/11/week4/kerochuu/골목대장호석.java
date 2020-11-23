package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 22.                   
public class 골목대장호석 {
	private static class Node {
		int idx;
		long cost;

		Node(int next, long cost) {
			this.idx = next;
			this.cost = cost;
		}
	}

	static ArrayList<Node>[] map;
	static int N, M, START, DEST;
	static long C;
	static long[] result;
	static Queue<Node> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		START = stoi(st.nextToken());
		DEST = stoi(st.nextToken());
		C = stol(st.nextToken());

		init();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			input(stoi(st.nextToken()), stoi(st.nextToken()), stol(st.nextToken()));
		}

		System.out.println(binarySearch());
	}

	private static long binarySearch() {
		long left = 1, right = C + 1, mid;

		while (left < right) {
			mid = (left + right) >> 1;
			if (isPossible(mid)) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right == C + 1 ? -1 : right;
	}

	private static boolean isPossible(long target) {
		initBfs();

		while (!q.isEmpty()) {
			Node now = q.poll();
			for (Node next : map[now.idx]) {
				long tempSum = result[now.idx] + next.cost;
				if (next.cost <= target && tempSum < result[next.idx]) {
					q.add(next);
					result[next.idx] = tempSum;
				}
			}
		}
		return result[DEST] <= C;
	}

	private static void initBfs() {
		for (int i = 1; i <= N; i++) {
			result[i] = Long.MAX_VALUE;
		}
		q = new LinkedList<>();
		q.add(new Node(START, 0));
		result[START] = 0;
	}

	private static void input(int a, int b, long cost) {
		map[a].add(new Node(b, cost));
		map[b].add(new Node(a, cost));
	}

	private static void init() {
		map = new ArrayList[N + 1];
		result = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
	
	private static long stol(String input) {
		return Long.parseLong(input);
	}
}
