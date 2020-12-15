package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 12. 12.                   
public class 스타트링크 {
	private static class Node {
		int now, count;

		Node(int now, int count) {
			this.now = now;
			this.count = count;
		}
	}

	static int F, S, G, U, D;
	static boolean[] visit;
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = stoi(st.nextToken());
		S = stoi(st.nextToken());
		G = stoi(st.nextToken());
		U = stoi(st.nextToken());
		D = stoi(st.nextToken());
		visit = new boolean[F + 1];

		q.add(new Node(S, 0));
		visit[S] = true;

		System.out.println(bfs());
	}

	private static String bfs() {
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.now == G) {
				return n.count + "";
			}

			isPossible(n.now + U, n.count);
			isPossible(n.now - D, n.count);
		}
		return "use the stairs";
	}

	private static void isPossible(int next, int nowCount) {
		if (next >= 1 && next <= F && !visit[next]) {
			visit[next] = true;
			q.add(new Node(next, nowCount + 1));
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
