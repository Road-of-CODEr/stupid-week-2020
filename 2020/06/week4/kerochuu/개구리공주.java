package baekjoon;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 6. 24.                   
public class 개구리공주 {

	private static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, X, Y, idx = 0;
	static char[] order;

	static ArrayList<Node> input = new ArrayList<>();
	static HashMap<Integer, TreeMap<Integer, Integer>> plus = new HashMap<>();
	static HashMap<Integer, TreeMap<Integer, Integer>> minus = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		order = br.readLine().toCharArray();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = stoi(st.nextToken());
			int y = stoi(st.nextToken());

			if (i == 0) {
				X = x;
				Y = y;
			} else if (isPossible(x, y)) {
				addPoint(x, y, idx++);
			}
		}

		for (int i = 0; i < M; i++) {
			move(order[i]);
		}

		System.out.println(X + " " + Y);
	}

	private static void move(char c) {
		switch (c) {
		case 'A':
			jump(minus, X - Y, false);
			break;
		case 'B':
			jump(plus, X + Y, false);
			break;
		case 'C':
			jump(plus, X + Y, true);
			break;
		case 'D':
			jump(minus, X - Y, true);
			break;
		}
	}

	private static void jump(HashMap<Integer, TreeMap<Integer, Integer>> hm, int idx, boolean isLeft) {
		if (hm.containsKey(idx)) {
			Entry<Integer, Integer> entry = isLeft ? hm.get(idx).lowerEntry(X) : hm.get(idx).higherEntry(X);
			if (entry != null) {
				X = input.get(entry.getValue()).x;
				Y = input.get(entry.getValue()).y;
				plus.get(X + Y).remove(entry.getKey());
				minus.get(X - Y).remove(entry.getKey());
			}
		}
	}

	private static void addPoint(int x, int y, int idx) {
		if (!plus.containsKey(x + y)) {
			plus.put(x + y, new TreeMap<>());
		}
		if (!minus.containsKey(x - y)) {
			minus.put(x - y, new TreeMap<>());
		}
		plus.get(x + y).put(x, idx);
		minus.get(x - y).put(x, idx);
		input.add(new Node(x, y));
		
	}

	private static boolean isPossible(int x, int y) {
		return (X % 2 + Y % 2) % 2 == (x % 2 + y % 2) % 2;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
