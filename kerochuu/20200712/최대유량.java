package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 7. 6.                   
public class 최대유량 {

	static int N, idx = 0, S, T;
	static HashMap<Character, Integer> hm = new HashMap<>();
	static int[][] capacity = new int[52][52];
	static int[][] flow = new int[52][52];
	static ArrayList<Integer>[] map = new ArrayList[52];
	static Queue<Integer> q = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		init();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			input(ctoi(st.nextToken()), ctoi(st.nextToken()), stoi(st.nextToken()));
		}

		S = hm.get('A');
		T = hm.get('Z');

		System.out.println(edmonds_karp());
	}

	private static int edmonds_karp() {
		int result = 0;
		int[] parents = new int[hm.size()];

		while (true) {
			Arrays.fill(parents, -1);
			q.clear();
			q.add(S);

			while (!q.isEmpty()) {
				int now = q.poll();
				for (int next : map[now]) {
					if (parents[next] == -1 && capacity[now][next] > flow[now][next]) {
						q.add(next);
						parents[next] = now;
						if (next == T) {
							break;
						}
					}
				}
			}

			if (parents[T] == -1) {
				break;
			}

			int min = Integer.MAX_VALUE;
			for (int i = T; i != S; i = parents[i]) {
				min = Math.min(min, capacity[parents[i]][i] - flow[parents[i]][i]);
			}

			for (int i = T; i != S; i = parents[i]) {
				flow[parents[i]][i] += min;
				flow[i][parents[i]] -= min;
			}
			result += min;
		}
		return result;
	}

	private static void input(int a, int b, int value) {
		capacity[a][b] += value;
		capacity[b][a] += value;
		map[a].add(b);
		map[b].add(a);
	}

	private static void init() {
		for (int i = 0; i < 52; i++) {
			map[i] = new ArrayList<>();
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}

	private static int ctoi(String input) {
		char c = input.charAt(0);
		if (hm.containsKey(c)) {
			return hm.get(c);
		} else {
			hm.put(c, idx);
			return idx++;
		}
	}
}
