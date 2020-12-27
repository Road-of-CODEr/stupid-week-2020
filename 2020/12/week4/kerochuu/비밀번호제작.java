package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 12. 26.                   
public class 비밀번호제작 {
	private static class Node {
		int num, count;

		Node(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}

	static int N, M, result = 0;
	static int[] visit;
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = stoi(br.readLine());
		M = stoi(br.readLine());

		init();

		int temp;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			temp = stoi(st.nextToken());
			q.add(new Node(temp, 0));
			visit[temp] = 0;
		}
		bfs();
		System.out.println(result);
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int temp = 1; temp <= N; temp <<= 1) {
				isPossible(n, (n.num & temp) > 0 ? (n.num - temp) : (n.num + temp));
			}
		}
	}

	private static void isPossible(Node n, int num) {
		if (num <= N && visit[num] == -1) {
			visit[num] = n.count + 1;
			q.add(new Node(num, n.count + 1));
			result = Math.max(result, n.count + 1);
		}
	}

	private static void init() {
		visit = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			visit[i] = -1;
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
