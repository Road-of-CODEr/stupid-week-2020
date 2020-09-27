package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 7. 12.                   
public class 중량제한 {
	private static class Node {
		int idx, cost;

		Node(int next, int cost) {
			this.idx = next;
			this.cost = cost;
		}
	}

	static int N, M, S, T, left = 0, right = -1;
	static ArrayList<Node>[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		init();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			right = Math.max(right, input(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()))+1);
		}

		st = new StringTokenizer(br.readLine());
		S = stoi(st.nextToken());
		T = stoi(st.nextToken());

		System.out.println(parametricSearch());
	}

	private static int parametricSearch() {
		int mid;
		int result = right;

		while (left < right) {
			mid = (left + right) >> 1;
			if (isPossible(mid)) {
				left = mid + 1;
				result = mid;
			} else {
				right = mid;
			}
		}
		return result;
	}

	private static boolean isPossible(int limit) {
		boolean[] visit = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<Integer>();

		q.add(S);
		visit[S] = true;

		while (!q.isEmpty()) {
			int here = q.poll();

			for (Node next : map[here]) {
				if (!visit[next.idx] && next.cost >= limit) {
					if (next.idx == T) {
						return true;
					}
					visit[next.idx] = true;
					q.add(next.idx);
				}
			}
		}
		return false;
	}

	private static int input(int a, int b, int cost) {
		map[a].add(new Node(b, cost));
		map[b].add(new Node(a, cost));
		return cost;
	}

	private static void init() {
		map = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
