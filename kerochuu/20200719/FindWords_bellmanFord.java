package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 7. 18.                   
public class FindWords_bellmanFord {

	private static class KeroList {
		String[] data;
		int size;
		int capacity = 4;

		KeroList() {
			data = new String[capacity];
			size = 0;
		}

		void init() {
			size = 0;
		}

		void reallocate() {
			capacity <<= 1;
			String[] temp = new String[capacity];
			for (int i = 0; i < size; i++) {
				temp[i] = data[i];
			}
			data = temp;
		}

		void add(String s) {
			if (capacity == size) {
				reallocate();
			}
			data[size++] = s;
		}

		String find(String s) {
			for (int i = 0; i < size; i++) {
				if (s.equals(data[i])) {
					return s;
				}
			}
			return "";
		}
	}

	private static class KeroSet {
		KeroList[] list;
		int size = 100007;

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

		void put(String s) {
			int hashCode = hash(s);
			list[hashCode].add(s);
		}

		int hash(String s) {
			int hashCode = 0;
			int key = 31;

			for (int i = 0; i < s.length(); i++) {
				hashCode = mod(hashCode + s.charAt(i) * key);
				key = mod(key << 1);
				key = mod(key * 23);
			}
			return hashCode;
		}

		String findWord(String sentence) {
			String temp;
			String result = "";

			for (int start = 0; start < sentence.length() - 6; start++) {
				StringBuilder sb = new StringBuilder();
				int hashCode = 0;
				int key = 31;
				for (int i = 0; i < 5; i++) {
					hashCode = mod(hashCode + sentence.charAt(i + start) * key);
					key = mod(key << 1);
					key = mod(key * 23);
					sb.append(sentence.charAt(i + start));
				}

				for (int i = 5; i < sentence.length() - start; i++) {
					hashCode = mod(hashCode + sentence.charAt(i + start) * key);
					key = mod(key << 1);
					key = mod(key * 23);
					sb.append(sentence.charAt(i + start));

					if ((temp = check(hashCode, sb.toString())) != "") {
						if (result != "") {
							return "AMBIGUOUS";
						} else {
							result = temp;
						}
					}
				}
			}
			return result == "" ? "NO" : result;
		}

		String check(int hashCode, String target) {
			return list[hashCode].find(target);
		}

		int mod(int hashCode) {
			return hashCode % size;
		}

	}

	static int N, M;
	static KeroSet keroSet = new KeroSet();
	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int tnum = stoi(st.nextToken());

		for (int t = 1; t <= tnum; t++) {
			keroSet.init();

			st = new StringTokenizer(br.readLine());
			N = stoi(st.nextToken());
			for (int i = 0; i < N; i++) {
				keroSet.put(br.readLine());
			}

			st = new StringTokenizer(br.readLine());
			M = stoi(st.nextToken());
			for (int i = 0; i < M; i++) {
				result.append(keroSet.findWord(br.readLine()) + "\n");
			}
		}
		System.out.print(result.toString());
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
