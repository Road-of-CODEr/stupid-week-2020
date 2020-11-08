package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 10. 10.                   
public class 트리의색깔과쿼리 {
	private static class Node {
		int type, V;

		Node(int type, int V) {
			this.type = type;
			this.V = V;
		}
	}

	static int N, Q;
	static int[] parents, next;
	static HashSet<Integer>[] hs;
	static Stack<Node> query = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		Q = stoi(st.nextToken());

		init();

		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			next[i] = stoi(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			hs[i].add(stoi(st.nextToken()));
		}

		for (int i = 0; i < N + Q - 1; i++) {
			st = new StringTokenizer(br.readLine());
			query.add(new Node(stoi(st.nextToken()), stoi(st.nextToken())));
		}

		System.out.print(calc());
	}

	private static String calc() {
		Stack<Integer> result = new Stack<>();
		StringBuilder sb = new StringBuilder();
		while (!query.isEmpty()) {
			Node n = query.pop();
			if (n.type == 1) {
				union(n.V, next[n.V]);
			} else {
				result.add(hs[find(n.V)].size());
			}
		}
		while (!result.isEmpty()) {
			sb.append(result.pop() + "\n");
		}
		return sb.toString();
	}

	private static int find(int a) {
		if (parents[a] < 0) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			if (hs[a].size() > hs[b].size()) {
				merge(b, a);
			} else {
				merge(a, b);
			}
		}
	}

	private static void merge(int a, int b) {
		for (int idx : hs[a]) {
			hs[b].add(idx);
		}
		hs[a].clear();
		parents[a] = b;
	}

	private static void init() {
		parents = new int[N + 1];
		hs = new HashSet[N + 1];
		next = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			hs[i] = new HashSet<>();
			parents[i] = -1;
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
