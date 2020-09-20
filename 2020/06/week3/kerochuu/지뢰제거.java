package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 6. 21.                   
public class Áö·ÚÁ¦°Å {

	private static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static ArrayList<Node> mine = new ArrayList<>();
	static HashSet<Integer> board = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tnum = stoi(st.nextToken());
		for (int t = 1; t <= tnum; t++) {
			init();
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = stoi(st.nextToken()) + 10;
				int y = stoi(st.nextToken()) + 10;
				board.add(convert(x, y));
				mine.add(new Node(x, y));
			}
			System.out.println(calc());
		}
	}

	private static int calc() {
		int result = -1;

		for (int i = 0; i < N; i++) {
			int mx = mine.get(i).x;
			int my = mine.get(i).y;

			result = Math.max(result, find(mx, mx + 10, my, my + 10));
			result = Math.max(result, find(mx - 10, mx, my, my + 10));
			result = Math.max(result, find(mx - 10, mx, my - 10, my));
			result = Math.max(result, find(mx, mx + 10, my - 10, my));
		}
		return result;
	}

	private static int find(int a, int b, int c, int d) {
		int count = 0;
		for (int i = a; i <= b; i++) {
			for (int j = c; j <= d; j++) {
				if (board.contains(convert(i, j))) {
					count++;
				}
			}
		}
		return count;
	}

	private static int convert(int x, int y) {
		return x * 10011 + y;
	}

	private static void init() {
		board.clear();
		mine.clear();
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
