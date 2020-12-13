package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 12. 13.                   
public class 소수경로 {
	private static class Node {
		int num, count;

		Node(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}

	static HashSet<Integer> hs = new HashSet<>();
	static boolean[] visit = new boolean[10000];
	static Queue<Node> q = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		init();
		int tnum = stoi(st.nextToken());

		for (int t = 1; t <= tnum; t++) {
			st = new StringTokenizer(br.readLine());
			q.clear();
			q.add(new Node(stoi(st.nextToken()), 0));
			sb.append(calc(stoi(st.nextToken())));
		}
		System.out.println(sb.toString());
	}

	private static String calc(int target) {
		visitInit();
		int temp;
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.num == target) {
				return n.count + "\n";
			}

			int a = n.num;
			int d = a % 10;
			a /= 10;
			int c = a % 10;
			a /= 10;
			int b = a % 10;
			a /= 10;

			for (int i = 1; i < 10; i++) {
				if (hs.contains(temp = convert(i, b, c, d)) && !visit[temp]) {
					visit[temp] = true;
					q.add(new Node(temp, n.count + 1));
				}
			}

			for (int i = 0; i < 10; i++) {
				if (hs.contains(temp = convert(a, i, c, d)) && !visit[temp]) {
					visit[temp] = true;
					q.add(new Node(temp, n.count + 1));
				}
			}

			for (int i = 0; i < 10; i++) {
				if (hs.contains(temp = convert(a, b, i, d)) && !visit[temp]) {
					visit[temp] = true;
					q.add(new Node(temp, n.count + 1));
				}
			}

			for (int i = 0; i < 10; i++) {
				if (hs.contains(temp = convert(a, b, c, i)) && !visit[temp]) {
					visit[temp] = true;
					q.add(new Node(temp, n.count + 1));
				}
			}
		}
		return "Impossible\n";
	}

	private static int convert(int a, int b, int c, int d) {
		return a * 1000 + b * 100 + c * 10 + d;
	}

	private static void visitInit() {
		for (int i = 0; i <= 1000; i++) {
			visit[i] = true;
		}
		for (int i = 1000; i < 10000; i++) {
			visit[i] = false;
		}
		visit[q.peek().num] = true;
	}

	private static void init() {
		for (int i = 2; i < 5000; i++) {
			for (int j = i + i; j < 10000; j += i) {
				visit[j] = true;
			}
		}
		for (int i = 2; i < 10000; i++) {
			if (!visit[i]) hs.add(i);
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
