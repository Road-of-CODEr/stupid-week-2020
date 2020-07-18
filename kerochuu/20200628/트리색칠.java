package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 6. 25.                   
public class Æ®¸®»öÄ¥ {

	static int N, root;
	static double[] cost, cnt;
	static int[] parent;
	static ArrayList<Integer>[] list;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		root = stoi(st.nextToken());

		init();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cnt[i] = 1;
			cost[i] = stoi(st.nextToken());
		}

		int u, v;
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			u = stoi(st.nextToken());
			v = stoi(st.nextToken());
			list[u].add(v);
			parent[v] = u;
		}
		System.out.println(calc());
	}

	private static int calc() {
		int dad, idx, allSum = 0;
		for (int i = 0; i < N - 1; i++) {
			idx = findMax();
			dad = find(parent[idx]);
			visit[idx] = true;

			allSum += cost[idx] * cnt[dad];
			update(idx, dad);
		}
		return (int) (allSum + cost[root]);
	}

	private static int find(int dad) {
		return visit[dad] ? parent[dad] = find(parent[dad]) : dad;
	}

	private static int findMax() {
		int idx = -1;
		double max = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			if (!visit[i] && i != root && max < (double) (cost[i] / cnt[i])) {
				max = (double) (cost[i] / cnt[i]);
				idx = i;
			}
		}
		return idx;
	}

	private static void update(int son, int dad) {
		cnt[dad] += cnt[son];
		cost[dad] += cost[son];
		parent[son] = dad;
	}

	private static void init() {
		cnt = new double[N + 1];
		cost = new double[N + 1];
		parent = new int[N + 1];
		visit = new boolean[N + 1];
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
