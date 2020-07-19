package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 7. 14.                   
public class 문자열잘라내기 {

	private static class KeroList {
		long[] data;
		int size, capacity = 8;

		KeroList() {
			data = new long[capacity];
			size = 0;
		}

		void init() {
			size = 0;
		}

		void reallocate() {
			capacity <<= 1;
			long[] temp = new long[capacity];
			for (int i = 0; i < size; i++) {
				temp[i] = data[i];
			}
			data = temp;
		}

		void add(long l) {
			if (size == capacity) {
				reallocate();
			}
			data[size++] = l;
		}

		boolean isContain(long l) {
			for (int i = 0; i < size; i++) {
				if (l == data[i]) {
					return true;
				}
			}
			return false;
		}
	}

	private static class KeroSet {
		KeroList[] list;
		int size = 10007;
		long mod = 98765432197531L;

		KeroSet() {
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

		boolean put(int idx, int start, int end) {
			int hash = hashCode_idx(idx, start, end);
			long key = hashCode_string(idx, start, end);
			if (list[hash].isContain(key)) {
				return true;
			}
			list[hash].add(key);
			return false;
		}

		int hashCode_idx(int idx, int start, int end) {
			int key = 23;
			int hash = 0;
			for (int i = start; i < end; i++) {
				hash = mod_idx(hash + table[i][idx] * key);
				key = mod_idx(key << 1);
			}
			return hash;
		}

		long hashCode_string(int idx, int start, int end) {
			long key = 10007;
			long hash = 0;
			for (int i = start; i < end; i++) {
				hash = mod_string(hash + table[i][idx] * key);
				key = mod_string(key << 2);
				key = mod_string(key * 23);
			}
			return hash;
		}

		int mod_idx(int hash) {
			return hash % size;
		}

		long mod_string(long hash) {
			return hash % mod;
		}

	}

	static int N, M;
	static char[][] table;
	static KeroSet keroSet = new KeroSet();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		table = new char[N][M];

		for (int i = 0; i < N; i++) {
			table[i] = br.readLine().toCharArray();
		}

		System.out.println(parametricSearch());

	}

	private static int parametricSearch() {
		int left = 0, right = N, result = N, mid;

		while (left < right) {
			mid = (left + right) >> 1;
			if (isRepeat(mid)) {
				right = mid;
				result = mid;
			} else {
				left = mid + 1;
			}
		}
		return result - 1;
	}

	private static boolean isRepeat(int mid) {
		keroSet.init();
		for (int i = 0; i < M; i++) {
			if (keroSet.put(i, mid, N)) {
				return true;
			}
		}
		return false;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
