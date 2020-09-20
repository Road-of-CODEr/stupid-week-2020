package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 7. 19.                   
public class 문자열집합판별 {
	private static class Node {
		boolean isEnd, isRoot;
		Node[] child;
		Node fail;

		Node(boolean isRoot) {
			child = new Node[size];
			this.isRoot = isRoot;
		}

		Node setChild(char data) {
			if (child[data - 'a'] == null) {
				return child[data - 'a'] = new Node(false);
			}
			return child[data - 'a'];
		}
	}

	private static class Trie {
		Node root = new Node(true); // true = root
		StringBuilder result = new StringBuilder();

		void add(String s) {
			Node n = root;
			for (int i = 0; i < s.length(); i++) {
				n = n.setChild(s.charAt(i));
			}
			n.isEnd = true;
		}

		// trie 디버깅용 print(), resurcive()
		void print() {
			recursive(0, 0, root, new StringBuilder());
			System.out.print(result.toString());
		}

		void recursive(int depth, int count, Node now, StringBuilder sb) {
			for (int i = 0; i < size; i++) {
				if (now.child[i] != null) {
					if (now.child[i].isEnd) {
						for (int j = 0; j < count; j++) {
							result.append(" ");
						}
						result.append(sb.toString() + ((char) (i + 'a')) + "\n");
						recursive(depth, count + 1, now.child[i], new StringBuilder());
					} else {
						sb.append((char) (i + 'a'));
						recursive(depth, count, now.child[i], sb);
						sb.deleteCharAt(sb.length() - 1);
					}
				}
			}
		}
	}

	static int size = 26;
	static Trie trie = new Trie();
	static Queue<Node> q = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// trie 구현
		int N = stoi(st.nextToken());
		for (int i = 0; i < N; i++) {
			trie.add(br.readLine());
		}
		// trie.print(); // trie 데이터 확인용 backTracking

		// bfs 구현 ( KMP 전처리 )
		initBfs();
		bfs();

		// KMP 구현
		st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		for (int i = 0; i < N; i++) {
			sb.append((kmp(br.readLine(), trie.root) ? "YES" : "NO") + "\n");
		}
		System.out.print(sb.toString());
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Node here = q.poll();
			for (int idx = 0; idx < size; idx++) {
				Node there = here.child[idx];
				if (there != null) {
					if (here.isRoot) { // root의 다음 depth까지 fail은 root 가르킴
						there.fail = trie.root;
					} else {
						Node failure = here.fail;
						while (!failure.isRoot&& failure.child[idx] == null) {
							failure = failure.fail;
						}
						if (failure.child[idx] != null) {
							failure = failure.child[idx];
						}
						there.fail = failure;
					}
					if (there.fail.isEnd) {
						there.isEnd = true;
					}
					q.add(there);
				}
			}
		}
	}

	private static boolean kmp(String input, Node n) {
		Node here = n; // 그냥 n 가져다 쓰면 trie.root 값 변함(call by ref.)
		for (int i = 0; i < input.length(); i++) {
			int there = input.charAt(i) - 'a';
			while (!here.isRoot && here.child[there] == null) {
				here = here.fail;
			}
			if (here.child[there] != null) {
				here = here.child[there];
			}
			if (here.isEnd) {
				return true;
			}
		}
		return false;
	}

	private static void initBfs() {
		trie.root.fail = trie.root; // root 회귀
		q.add(trie.root);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
