package baekjoon;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 12. 20.                   
public class 개미굴 {
	private static class Node {
		TreeMap<String, Node> tm;
		Node() {
			tm = new TreeMap<>();
		}
	}

	private static class Trie {
		Node root;
		String s;

		Trie() {
			root = new Node();
		}

		void add(int size, StringTokenizer st) {
			Node temp = root;
			for (int i = 0; i < size; i++) {
				if (!temp.tm.containsKey(s = st.nextToken())) {
					temp.tm.put(s, new Node());
				}
				temp = temp.tm.get(s);
			}
		}

		void dfs(Node now, int depth) {
			for (Entry<String, Node> e : now.tm.entrySet()) {
				for (int i = 0; i < depth; i++) sb.append("--");
				sb.append(e.getKey() + "\n");
				dfs(e.getValue(), depth + 1);
			}
		}
	}

	static int N;
	static Trie trie = new Trie();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			trie.add(stoi(st.nextToken()), st);
		}

		trie.dfs(trie.root, 0);
		System.out.println(sb.toString());
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
