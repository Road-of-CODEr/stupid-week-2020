import java.io.*;
import java.util.*;

// @address : blog.naver.com/kerochuu/221990849615
// @date : 2020. 6. 6.
public class 디스크트리 {
	private static class Node {
		char data;
		Node[] child;

		Node() {
			child = new Node[size];
		}

		Node(char c) {
			data = c;
			child = new Node[size];
		}

		Node setChild(char data) {
			if (child[getIdx(data)] == null) {
				return child[getIdx(data)] = new Node(data);
			}
			return child[getIdx(data)];
		}
	}

	private static class Trie {
		Node root = new Node();
		StringBuilder result = new StringBuilder();

		void add(String s) {
			Node n = root;
			for (int i = 0; i < s.length(); i++) {
				n = n.setChild(s.charAt(i));
			}
			n.setChild('\\');
		}

		void print() {
			recursive(0, 0, root, new StringBuilder());
			result.deleteCharAt(result.length() - 1);
			System.out.print(result.toString());
		}

		void recursive(int depth, int count, Node now, StringBuilder sb) {
			for (int i = 0; i < size; i++) {
				if (now.child[i] != null) {
					if (i == 0) {
						for (int j = 0; j < count; j++) {
							result.append(" ");
						}
						result.append(sb.toString() + "\n");
						recursive(depth, count + 1, now.child[i], new StringBuilder());
					} else {
						sb.append(now.child[i].data);
						recursive(depth, count, now.child[i], sb);
						sb.deleteCharAt(sb.length() - 1);
					}
				}
			}
		}
	}

	static int N, size = 53;
	static HashMap<Character, Integer> hm = new HashMap<>();
	static Trie trie = new Trie();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());

		init();

		for (int i = 0; i < N; i++) {
			trie.add(br.readLine());
		}
		trie.print();
	}

	private static void init() {
		String special = "!#$%&'()-@^_`{}~ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		char[] data = special.toCharArray();
		Arrays.sort(data);
		int idx = 1;
		for (int i = 0; i < data.length; i++) {
			hm.put(data[i], idx++);
		}
	}

	private static int getIdx(char c) {
		return c == '\\' ? 0 : hm.get(c);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
