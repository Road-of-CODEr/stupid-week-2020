package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 12. 6.                   
public class 준오는최종인재야 {
	private static class Node {
		int idx, cost;

		Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	static int N, T, max, dest, result, farthestNode;
	static ArrayList<Node>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		T = stoi(st.nextToken());

		init();

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			input(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
		}

		max = 0;
		dfs(1, 0, 0, -1);

		max = 0;
		dfs(dest, 0, 0, -1);

		System.out.println((result / T + (result % T == 0 ? 0 : 1)));
	}

	private static void dfs(int now, int depth, int allSum, int parent) {
		if (depth > max) {
			max = depth;
			result = allSum;
			dest = now;
		} else if (depth == max && allSum < result) {
			result = allSum;
			dest = now;
		}

		for (Node next : list[now]) {
			if (next.idx != parent) {
				dfs(next.idx, depth + 1, allSum + next.cost, now);
			}
		}
	}

	private static void input(int a, int b, int cost) {
		list[a].add(new Node(b, cost));
		list[b].add(new Node(a, cost));
	}

	private static void init() {
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
