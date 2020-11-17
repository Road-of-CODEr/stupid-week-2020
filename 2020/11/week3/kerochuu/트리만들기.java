package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 12.                   
public class 트리만들기 {
	private static class Node implements Comparable<Node> {
		int a, b;

		Node(int a, int b) {
			if (a > b) {
				this.a = b;
				this.b = a;
			} else {
				this.a = a;
				this.b = b;
			}
		}

		@Override
		public int compareTo(Node o) {
			return this.a == o.a ? this.b - o.b : this.a - o.a;
		}
	}

	static int N;
	static boolean[] pick;
	static int[] num;
	static PriorityQueue<Integer> pq;
	static PriorityQueue<Node> result;
	static HashMap<Integer, Integer> hm = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		init();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N - 2; i++) {
			pick[num[i] = stoi(st.nextToken())] = true;
			if (hm.containsKey(num[i])) {
				hm.put(num[i], hm.get(num[i]) + 1);
			} else {
				hm.put(num[i], 1);
			}
		}

		System.out.println(isPossible() ? print() : -1);
	}

	private static boolean isPossible() {
		int idx = -1;
		for (int i = N; i >= 1; i--) {
			if (!pick[i]) {
				pq.add(i);
			}
		}

		while (!pq.isEmpty() && ++idx < N - 2) {
			hm.put(num[idx], hm.get(num[idx]) - 1);
			result.add(new Node(pq.poll(), num[idx]));
			if (hm.get(num[idx]) == 0) {
				pq.add(num[idx]);
			}
		}

		return pq.size() == 2;
	}

	private static String print() {
		StringBuilder sb = new StringBuilder();
		result.add(new Node(pq.poll(), pq.poll()));
		while (!result.isEmpty()) {
			sb.append(result.peek().a + " " + result.poll().b + "\n");
		}
		return sb.toString();
	}

	private static void init() {
		num = new int[N - 2];
		pick = new boolean[N + 1];

		pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		result = new PriorityQueue<>();
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
