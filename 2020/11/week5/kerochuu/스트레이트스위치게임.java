package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 27.                   
public class 스트레이트스위치게임 {
	private static class Node implements Comparable<Node> {
		int[] cube;
		int[] count;
		int depth;

		Node(int[] cube, int[] count, int depth) {
			this.cube = cube;
			this.count = count;
			this.depth = depth;
		}

		@Override
		public int compareTo(Node o) {
			return this.depth - o.depth;
		}

	}

	static int[] origin;
	static int N, M;
	static ArrayList<Integer>[] pick;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static HashSet<Long> visit = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		init();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			origin[i] = stoi(st.nextToken());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			input(i, stoi(st.nextToken()), st);
		}

		System.out.println(simulation());
	}

	private static int simulation() {
		pq.add(new Node(origin, new int[M], 0));
		visit.add(convert(pq.peek().cube));
		int[] cube;
		int[] count = new int[M];
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			if (isSameNum(n.cube)) {
				return n.depth;
			}
			for (int i = 0; i < n.count.length; i++) {
				count[i]++;
				cube = calc(n.cube, count).clone();
				if (!visit.contains(convert(cube))) {
					visit.add(convert(cube));
					pq.add(new Node(cube.clone(), count.clone(), n.depth + 1));
				}
				count[i]--;
			}
		}
		return -1;
	}

	private static boolean isSameNum(int[] target) {
		for (int i = 1; i < target.length; i++) {
			if (target[i - 1] != target[i]) {
				return false;
			}
		}
		return true;
	}

	private static int[] calc(int[] cube, int[] count) {
		int[] target = cube.clone();
		for (int i = 0; i < count.length; i++) {
			for (int idx : pick[i]) {
				target[idx] = (target[idx] + (i + 1) * count[i]) % 5;
			}
		}
		return target;
	}

	private static long convert(int[] list) {
		long l = 0;
		for (int i = 0; i < list.length; i++, l *= 10) {
			l += list[i];
		}
		return l;
	}

	private static void input(int idx, int size, StringTokenizer st) {
		for (int i = 0; i < size; i++) {
			pick[idx].add(stoi(st.nextToken()) - 1);
		}
	}

	private static void init() {
		origin = new int[N];
		pick = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			pick[i] = new ArrayList<>();
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
