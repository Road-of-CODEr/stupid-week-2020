package baekjoon;

import java.util.Scanner;
import java.util.TreeSet;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 6. 21.                   
public class 좋은수열 {

	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		backTracking(1);
		System.out.println(sb.toString());
	}

	private static boolean backTracking(int depth) {
		if (depth == N + 1) {
			return true;
		}

		for (int i = 1; i <= 3; i++) {
			sb.append(i);
			if (isGood() && backTracking(depth + 1)) {
				return true;
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return false;
	}

	private static boolean isGood() {
		
		int start = sb.length() - 1;
		int end = sb.length();

		for (int i = 1; i <= sb.length() >> 1; start--, i++) {
			if (sb.substring(start, end).equals(sb.substring(start - i, end - i))) {
				return false;
			}
		}
		return true;
	}
}
