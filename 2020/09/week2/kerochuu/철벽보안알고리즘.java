package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 9. 12.                   
public class 철벽보안알고리즘 {
	private static class Node {
		String key;
		int value;

		Node(String key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	private static class KeroList {
		Node[] data;
		int size, capacity = 8;

		KeroList() {
			data = new Node[capacity];
			size = 0;
		}

		void init() {
			size = 0;
		}

		void reallocate() {
			capacity <<= 1;
			Node[] temp = new Node[capacity];
			for (int i = 0; i < size; i++) {
				temp[i] = data[i];
			}
			data = temp;
		}

		void add(Node n) {
			if (capacity == size) {
				reallocate();
			}
			data[size++] = n;
		}

		Node get(int idx) {
			return data[idx];
		}

	}

	private static class KeroMap {
		KeroList[] list;
		int size = 100003;

		KeroMap() {
			list = new KeroList[size];
			for (int i = 0; i < size; i++) {
				list[i] = new KeroList();
			}
		}

		void init() {
			for (int i = 0; i < size; i++) {
				list[i].init();
			}
		}

		void put(String s, int value) {
			int hash = hashCode(s);
			list[hash].add(new Node(s, value));
		}

		int get(String s) {
			int hash = hashCode(s);
			for (int i = 0; i < list[hash].size; i++) {
				if (list[hash].get(i).key.equals(s)) {
					return list[hash].get(i).value;
				}
			}
			return -1;
		}

		int hashCode(String s) {
			int hash = 0;
			int key = 1;
			for (int i = 0; i < s.length(); i++) {
				hash = mod(hash + key * s.charAt(i));
				key = mod((key + 53) << 2);
			}
			return hash;
		}

		int mod(int num) {
			return num % size;
		}

	}

	static int[] next;
	static KeroMap keroMap = new KeroMap();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tnum = stoi(st.nextToken());

		for (int t = 1; t <= tnum; t++) {
			keroMap.init();

			st = new StringTokenizer(br.readLine());
			int N = stoi(st.nextToken());
			next = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				keroMap.put(st.nextToken(), i);
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				next[i] = keroMap.get(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			String[] result = new String[N];
			for (int i = 0; i < N; i++) {
				result[next[i]] = st.nextToken();
			}

			for (int i = 0; i < N; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
