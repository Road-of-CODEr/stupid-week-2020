package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 7. 5.                   
public class Áß¾ÓÆ®¸® {

	private static class Node {
		int next, cost;

		Node(int next, int value) {
			this.next = next;
			this.cost = value;
		}
	}

	static int N;
	static long[] size, myFamily, stranger;
	static ArrayList<Node>[] tree;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while ((N = stoi(br.readLine())) != 0) {
			init();
			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int cost = stoi(st.nextToken());
				tree[a].add(new Node(b, cost));
				tree[b].add(new Node(a, cost));
			}
			dfs1(0, -1);
			dfs2(0, -1);
			sb.append(findResult() + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs1(int here, int dad) {
		for (Node n : tree[here]) {
			if (n.next != dad) {
				dfs1(n.next, here);
				size[here] += size[n.next];
				myFamily[here] += myFamily[n.next] + size[n.next] * n.cost;
			}
		}
		size[here]++; // size countingÀÇ ÇÙ½É
	}

	private static void dfs2(int here, int dad) {
		for (Node n : tree[here]) {
			if (n.next != dad) {
				stranger[n.next] += stranger[here] + (size[0] - size[n.next]) * n.cost + myFamily[here]
						- (myFamily[n.next] + size[n.next] * n.cost);
				dfs2(n.next, here);
			}
		}
	}

	private static long findResult() {
		long min = Long.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, myFamily[i] + stranger[i]);
		}
		return min;
	}

	private static void init() {
		size = new long[N];
		myFamily = new long[N];
		stranger = new long[N];
		tree = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
