package baekjoon;

import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 9. 6.                   
public class 신기한소수2 {

	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();

		dfs(0, 0);
		System.out.println(sb.toString());
	}

	public static void dfs(int allSum, int depth) {
		if (depth == N) {
			sb.append(allSum + "\n");
		} else {
			for (int i = 1; i <= 9; i++) {
				if (isPrime(allSum * 10 + i)) {
					dfs(allSum * 10 + i, depth + 1);
				}
			}
		}
	}

	public static boolean isPrime(int k) {
		if (k == 1) {
			return false;
		}

		int sqrt = (int) Math.sqrt(k);
		for (int i = 2; i <= sqrt; i++) {
			if (k % i == 0) {
				return false;
			}
		}
		return true;
	}
}
