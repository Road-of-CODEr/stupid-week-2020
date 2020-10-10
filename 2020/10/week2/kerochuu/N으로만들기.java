package baekjoon;

import java.io.*;
import java.util.HashSet;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 10. 10.                   
public class N으로만들기 {
	private static class KeroList {
		String[] data;
		StringBuilder sb;
		int size, capacity = 8;

		KeroList() {
			data = new String[capacity];
			sb = new StringBuilder();
			size = 0;
		}

		void reallocate() {
			String[] temp = new String[capacity <<= 1];
			for (int i = 0; i < size; i++) {
				temp[i] = data[i];
			}
			data = temp;
		}

		void add(String s) {
			if (size == capacity) {
				reallocate();
			}
			data[size++] = s;
		}

		void remove() {
			size--;
		}

		String print() {
			sb = new StringBuilder();
			for (int i = 0; i < size; i++) {
				sb.append(data[i]);
			}
			return sb.toString();
		}
	}

	static String input;
	static KeroList keroList = new KeroList();
	static HashSet<String> hs = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();

		recursive(0, input.length());
		System.out.println(hs.size());
	}

	private static void recursive(int left, int right) {
		if (left == right - 1) {
			hs.add(keroList.print());
			return;
		}

		keroList.add(input.substring(left + 1, right));
		recursive(left + 1, right);
		keroList.remove();
		keroList.add(input.substring(left, right - 1));
		recursive(left, right - 1);
		keroList.remove();
	}
}
