package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 20.                   
public class 문자열지옥 {

	private static class Node {
		char[] word;
		int count;

		Node(char[] word, int count) {
			this.word = word;
			this.count = count;
		}
	}

	private static class KeroList {
		Node[] data;
		int size, capacity = 8;

		KeroList() {
			data = new Node[capacity];
			size = 0;
		}

		void reallocate() {
			Node[] temp = new Node[capacity <<= 1];
			for (int i = 0; i < size; i++) {
				temp[i] = data[i];
			}
			data = temp;
		}

		void add(Node n) {
			if (crash(n, true)) {
				return;
			}
			if (size == capacity) {
				reallocate();
			}
			data[size++] = n;
		}

		boolean crash(Node n, boolean isCount) {
			lp: for (int i = 0; i < size; i++) {
				if (data[i].word.length == n.word.length) {
					for (int j = 0; j < n.word.length; j++) {
						if (data[i].word[j] != n.word[j]) {
							continue lp;
						}
					}
					if (isCount) {
						data[i].count++;
					}
					return true;
				}
			}
			return false;
		}

		int get(char[] word) {
			lp: for (int i = 0; i < size; i++) {
				if (data[i].word.length == word.length) {
					for (int j = 0; j < word.length; j++) {
						if (data[i].word[j] != word[j]) {
							continue lp;
						}
					}
					return data[i].count;
				}
			}
			return 0;
		}

	}

	private static class KeroMap {
		KeroList[] list;

		KeroMap() {
			list = new KeroList[SIZE];
			for (int i = 0; i < SIZE; i++) {
				list[i] = new KeroList();
			}
		}

		void put(char[] word) {
			list[hashCode(word)].add(new Node(word, 1));
		}

		int hashCode(char[] word) {
			int hashCode = 0;
			int key = 23;

			for (int i = 0; i < word.length; i++) {
				hashCode = mod(hashCode + word[i] * key);
				key = mod((key << 1) * 57);
			}
			return hashCode;
		}

		int mod(int hashCode) {
			return hashCode % SIZE;
		}

		int isContain(char[] word) {
			return list[hashCode(word)].get(word);
		}

	}

	static int R, C, K, SIZE = 100007;
	static int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };
	static char[] pick = new char[5];
	static char[][] map;
	static KeroMap keroMap = new KeroMap();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = stoi(st.nextToken());
		C = stoi(st.nextToken());
		K = stoi(st.nextToken());

		map = new char[R][C];

		char[] input;
		for (int i = 0; i < R; i++) {
			input = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = input[j];
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				backTracking(i, j, 0);
			}
		}

		for (int i = 0; i < K; i++) {
			input = br.readLine().toCharArray();
			sb.append(keroMap.isContain(input) + "\n");
		}

		System.out.print(sb.toString());
	}

	private static void backTracking(int x, int y, int depth) {
		if (depth == 5) {
			return;
		}
		pick[depth] = map[x][y];
		keroMap.put(convert(pick, depth));

		for (int i = 0; i < 8; i++) {
			int nx = next(x, dx[i], R);
			int ny = next(y, dy[i], C);
			backTracking(nx, ny, depth + 1);
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}

	private static char[] convert(char[] pick, int size) {
		char[] temp = new char[size + 1];
		for (int i = 0; i <= size; i++) {
			temp[i] = pick[i];
		}
		return temp;
	}

	private static int next(int x, int dir, int plus) {
		return x + dir < 0 ? x + dir + plus : (x + dir) % plus;
	}
}
