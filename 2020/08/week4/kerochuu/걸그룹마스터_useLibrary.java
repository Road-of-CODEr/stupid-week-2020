package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 23.                   
public class 걸그룹마스터 {

	private static class KeroList {
		Object[] data;
		int size, capacity = 8;

		KeroList() {
			data = new Object[capacity];
			size = 0;
		}

		void reallocate() {
			Object[] temp = new Object[capacity <<= 1];
			for (int i = 0; i < size; i++) {
				temp[i] = data[i];
			}
			data = temp;
		}

		void add(Object s) {
			if (size == capacity) {
				reallocate();
			}
			data[size++] = s;
		}

		void sort() {
			Arrays.sort(data);
		}

		String get(int idx) {
			return (String) data[idx];
		}

		Node2 getNode2(int idx) {
			return (Node2) data[idx];
		}

		Node getNode(int idx) {
			return (Node) data[idx];
		}

	}

	private static class Node {
		String s;
		KeroList list;

		Node(String group, String name) {
			this.s = group;
			this.list = new KeroList();
			this.list.add(name);
		}
	}

	private static class Node2 {
		String group, name;

		Node2(String group, String name) {
			this.group = group;
			this.name = name;
		}
	}

	private static class GroupMap {
		KeroList[] list;
		int size = 1337;

		GroupMap() {
			list = new KeroList[size];
			for (int i = 0; i < size; i++) {
				list[i] = new KeroList();
			}
		}

		void put(String group, String name) {
			int hash = hashCode(group);
			for (int i = 0; i < list[hash].size; i++) {
				if (list[hash].getNode(i).s.equals(group)) {
					list[hash].getNode(i).list.add(name);
					return;
				}
			}
			list[hash].add(new Node(group, name));
		}

		int hashCode(String s) {
			int hash = 0;
			int key = 31;

			for (int i = 0; i < s.length(); i++) {
				hash = mod(hash + s.charAt(i) * key);
				key = mod(key << 1);
			}
			return hash;
		}

		void get(String s) {
			int hash = hashCode(s);
			for (int i = 0; i < list[hash].size; i++) {
				if (list[hash].getNode(i).s.equals(s)) {
					KeroList temp = list[hash].getNode(i).list;
					for (int j = 0; j < temp.size; j++) {
						pq.add(temp.get(j));
					}
					return;
				}
			}
		}

		void sort(String s) {
			int hash = hashCode(s);
			list[hash].sort();
			// Collections.sort(list);
		}

		int mod(int hash) {
			return hash % size;
		}
	}

	private static class NameMap {
		KeroList[] list;
		int size = 1337;

		NameMap() {
			list = new KeroList[size];
			for (int i = 0; i < size; i++) {
				list[i] = new KeroList();
			}
		}

		void put(String name, String group) {
			int hash = hashCode(name);
			list[hash].add(new Node2(name, group));
		}

		int hashCode(String s) {
			int hash = 0;
			int key = 31;

			for (int i = 0; i < s.length(); i++) {
				hash = mod(hash + s.charAt(i) * key);
				key = mod(key << 1);
			}
			return hash;
		}

		void get(String s) {
			int hash = hashCode(s);
			for (int i = 0; i < list[hash].size; i++) {
				if (list[hash].getNode2(i).group.equals(s)) {
					pq.add(list[hash].getNode2(i).name);
					return;
				}
			}
		}

		int mod(int hash) {
			return hash % size;
		}
	}

	static int N, M;
	// static HashMap<String, String> nameMap = new HashMap<>();
	// static HashMap<String, ArrayList<String>> groupMap = new HashMap<>();
	static StringBuilder sb = new StringBuilder();

	static GroupMap groupMap = new GroupMap();
	static NameMap nameMap = new NameMap();
	static PriorityQueue<String> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		for (int i = 0; i < N; i++) {
			String group = br.readLine();
			int size = stoi(br.readLine());
			// groupMap.put(group, new ArrayList<String>());
			for (int j = 0; j < size; j++) {
				String name = br.readLine();
				nameMap.put(name, group);
				groupMap.put(group, name);
			}
		}

		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			if (br.readLine().charAt(0) == '0') {
				groupMap.get(s);
			} else {
				nameMap.get(s);
			}
			while (!pq.isEmpty()) {
				sb.append(pq.poll() + "\n");
			}
		}
		System.out.print(sb.toString());
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
