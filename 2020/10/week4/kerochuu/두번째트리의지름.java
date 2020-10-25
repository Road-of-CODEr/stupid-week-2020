package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 10. 25.                   
public class 두번째트리의지름 {
	private static class Node {
		int next, cost;

		Node(int next, int cost) {
			this.next = next;
			this.cost = cost;
		}
	}

	static int N, maxLength, tempResult, max1, max2, target1, target2;
	static ArrayList<Node>[] map;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());

		init();

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			input(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
		}

		visitInit(1);
		dfs(1, 0, 0);
		max1 = tempResult;

		visitInit(max1);
		dfs(max1, 0, 0);
		max2 = tempResult;

		visitInit(max1);
		dfs(max1, 0, max2);
		target1 = maxLength;

		visitInit(max2);
		dfs(max2, 0, max1);
		target2 = maxLength;

		System.out.println(Math.max(target1, target2));
	}

	private static void dfs(int now, int allCost, int exclusive) {
		if (maxLength < allCost) {
			maxLength = allCost;
			tempResult = now;
		}
		for (Node n : map[now]) {
			if (!visit[n.next] && n.next != exclusive) {
				visit[n.next] = true;
				dfs(n.next, allCost + n.cost, exclusive);
			}
		}
	}

	private static void visitInit(int starting) {
		for (int i = 1; i <= N; i++) {
			visit[i] = false;
		}
		visit[starting] = true;
		maxLength = -1;
	}

	private static void input(int a, int b, int cost) {
		map[a].add(new Node(b, cost));
		map[b].add(new Node(a, cost));
	}

	private static void init() {
		map = new ArrayList[N + 1];
		visit = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
