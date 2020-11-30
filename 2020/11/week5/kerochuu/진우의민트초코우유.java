package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 27.                   
public class 진우의민트초코우유 {
	private static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, H, result = 0;
	static int[] pick;
	static boolean[] visit;
	static Node home;
	static ArrayList<Node> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		H = stoi(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				switch (stoi(st.nextToken())) {
				case 1:
					home = new Node(i, j);
					break;
				case 2:
					list.add(new Node(i, j));
					break;
				}
			}
		}
		pick = new int[list.size()];
		visit = new boolean[list.size()];
		permutation(0);

		System.out.println(result);
	}

	private static void permutation(int depth) {
		if (depth > result) simulation(depth);
		// simulation(depth);
		if (depth != list.size()) {
			for (int i = 0; i < list.size(); i++) {
				if (!visit[i]) {
					visit[i] = true;
					pick[depth] = i;
					permutation(depth + 1);
					visit[i] = false;
				}
			}
		}
	}

	private static void simulation(int size) {
		int count = 0, hp = M, x = home.x, y = home.y, cost;
		for (int i = 0; i < size; i++) {
			cost = Math.abs(x - list.get(pick[i]).x) + Math.abs(y - list.get(pick[i]).y);
			if (cost <= hp) {
				hp += H - cost;
				x = list.get(pick[i]).x;
				y = list.get(pick[i]).y;
				count++;
			} else {
				return;
			}
		}
		if (Math.abs(x - home.x) + Math.abs(y - home.y) <= hp) {
			result = Math.max(result, count);
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
