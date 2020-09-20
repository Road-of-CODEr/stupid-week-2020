package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 7. 18.                   
public class findWords_Trie {
	private static class Node {
		boolean isEnd;
		Node[] next;

		Node() {
			isEnd = false;
			next = new Node[3];
		}

		Node getChild(char c) {
			int idx = c - 'a';
			n = null;
			for (int i = 0; i < 3; i++) {
				if (next[idx % 3] == null) {
					return null;
				}
				n = next[idx % 3];
				idx /= 3;
			}
			return n;
		}

		Node setChild(char c) {
			int idx = c - 'a';
			n = null;
			for (int i = 0; i < 3; i++) {
				if (next[idx % 3] == null) {
					next[idx % 3] = new Node();
				}
				n = next[idx % 3];
				idx /= 3;
			}
			return n;
		}
	}

	private static class Trie {
		Node root;

		Trie() {
			root = new Node();
		}

		void setString(String s) {
			n = root;
			for (int i = 0; i < s.length(); i++) {
				n = n.setChild(s.charAt(i));
			}
			n.isEnd = true;
		}

		String isContain(char[] input) {
			temp = "";
			lp: for (int i = 0; i < input.length - 6; i++) {
				n = root;
				for (int j = i; j < input.length; j++) {
					n = n.getChild(input[j]);
					if (n == null) {
						continue lp;
					}
					if (n.isEnd) {
						if (temp == "") {
							temp = String.copyValueOf(input, i, j - i + 1);
						} else {
							return "AMBIGUOUS";
						}
					}
				}
			}
			return temp == "" ? "NO" : temp;
		}
	}

	static Node n;
	static String temp;
	static int N, M;
	static Trie trie;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int tnum = stoi(st.nextToken());

		for (int t = 1; t <= tnum; t++) {
			trie = new Trie();
			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			for (int i = 0; i < N; i++) {
				trie.setString(br.readLine());
			}

			st = new StringTokenizer(br.readLine());
			M = stoi(st.nextToken());
			for (int i = 0; i < M; i++) {
				sb.append(trie.isContain(br.readLine().toCharArray()) + "\n");
			}
		}
		System.out.print(sb.toString());
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}

}
