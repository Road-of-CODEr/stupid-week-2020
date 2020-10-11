package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 9. 30.                   
public class ÇÐÈ¸¿ø {

	private static class Node {
		String key;
		KeroList value;

		Node(String key, KeroList value) {
			this.key = key;
			this.value = value;
		}
		
		void init() {
			ket = "";
			value.init();
		}
	}

	private static class KeroList {
		String[] value;
		int size, capacity = 8;

		KeroList() {
			value = new String[capacity];
			size = 0;
		}

		void init() {
			size = 0;
		}

		void reallocate() {
			String[] temp = new String[capacity <<= 1];
			for (int i = 0; i < size; i++) {
				temp[i] = value[i];
			}
			value = temp;
		}

		void add(String s) {
			if (size == capacity) {
				reallocate();
			}
			value[size++] = s;
		}

		String get(int idx) {
			return value[idx];
		}

		int size() {
			return size;
		}
	}

	private static class KeroMap {
		int size = 10003;
		Node[] data = new Node[size];

		KeroMap() {
			for (int i = 0; i < size; i++) {
				data[i] = new Node();
			}
		}

		void init() {
			for (int i = 0; i < size; i++) {
				data[i].init();
			}
		}

		void put(String s) {
			if (!isContain(s)) {
				data[hashCode(s)].add(s);
			}
		}

		boolean isContain(String s) {
			int hash = hashCode(s);
			for (int i = 0; i < data[hash].size(); i++) {
				if (data[hash].get(i).equals(s)) {
					return true;
				}
			}
			return false;
		}

		KeroList get(String s) {
			return data[hashCode(s)];
		}

		int hashCode(String s) {
			int hash = 0, key = 73;
			for (int i = 0; i < s.length(); i++) {
				hash = mod(hash + s.charAt(i) * key);
				key = mod(key * 137);
			}
			return hash;
		}

		int mod(int idx) {
			return idx % size;
		}
	}

	static int N, count;
	static KeroMap keroMap = new KeroMap();
	static String target;

	static HashMap<String, ArrayList<String>> hm = new HashMap<>();
	static HashSet<String> visit = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while ((N = stoi(br.readLine())) != 0) {
//			keroMap.init();
			hm.clear();
			visit.clear();
			count = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), ":|,|.");

				String key = st.nextToken();
				if (i == 0) {
					target = key;
				}
				if(!keroMap.isContain(key)) {
					keroMap.put(s, );
				}
				if (!hm.containsKey(key)) {
					hm.put(key, new ArrayList<>());
				}
				while (st.hasMoreTokens()) {
					hm.get(key).add(st.nextToken());
				}
			}
			visit.add(target);
			System.out.println(recursive(target, 0));
		}
	}

	private static int recursive(String key, int count) {
		if (hm.containsKey(key)) {
			for (String child : hm.get(key)) {
				if (!visit.contains(child)) {
					visit.add(child);
					count += recursive(child, hm.get(child) == null ? 1 : 0);
				}
			}
		}
		return count;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
