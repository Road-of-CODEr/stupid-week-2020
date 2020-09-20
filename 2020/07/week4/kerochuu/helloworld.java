package baekjoon;

import java.util.Scanner;

//@author : blog.naver.com/kerochuu 
//@date : 2020. 7. 26.                               
public class helloworld {

	static int target;
	static int[] num = new int[7];
	static boolean[] pick = new boolean[10];

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		target = input.nextInt();
		System.out.println(backTracking(0) ? print() : "No Answer");
	}

	private static boolean backTracking(int depth) {
		if (depth == 7) {
			return isPossible();
		}

		for (int i = 0; i <= 9; i++) {
			if (!pick[i]) {
				pick[i] = true;
				num[depth] = i;
				if (backTracking(depth + 1)) {
					return true;
				}
				pick[i] = false;
			}
		}
		return false;
	}

	private static String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("  " + num[2] + num[1] + num[3] + num[3] + num[4] + "\n" 
		+ "+ " + num[6] + num[4] + num[5] + num[3]
				+ num[0] + "\n" + "-------\n");
		for (int i = String.valueOf(target).length(); i < 7; i++) {
			sb.append(" ");
		}
		sb.append(String.valueOf(target));
		return sb.toString();

	}

	private static boolean isPossible() {
		if(num[2] == 0 || num[6] == 0) {
			return false;
		}
		
		return (num[0] + num[1] * 1000 + num[2] * 10000 + num[3] * 2 * 10 + num[3] * 100 + num[4] + num[4] * 1000
				+ num[5] * 100 + num[6] * 10000) == target;
	}
}
