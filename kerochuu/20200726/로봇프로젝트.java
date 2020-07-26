package baekjoon;

import java.io.*;
import java.util.Arrays;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 7. 26.                   
public class 로봇프로젝트 {

	static long target, size;
	static long[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while ((target = stoi(br.readLine()) * 10000000) != -1) {
			size = stoi(br.readLine());
			
			list = new long[(int) size];

			for (int i = 0; i < size; i++) {
				list[i] = stoi(br.readLine());
			}

			System.out.println(calc());
		}
	}

	private static String calc() {
		Arrays.sort(list);
		long left = 0;
		long right = size - 1;

		while (left < right) {
			long sum = list[(int) left] + list[(int) right];
			if (isPossible(sum)) {
				return "yes " + list[(int) left] + " " + list[(int) right];
			}

			if (pushLeft(sum)) {
				left++;
			} else {
				right--;
			}
		}
		return "danger";
	}

	private static boolean isPossible(long sum) {
		return sum == target;
	}

	private static boolean pushLeft(long sum) {
		return sum < target;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
