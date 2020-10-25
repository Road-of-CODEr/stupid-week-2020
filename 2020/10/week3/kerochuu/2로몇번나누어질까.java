package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 10. 18.                   

public class 몇번나누어질까 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Scanner input = new Scanner(System.in);
		System.out.println(-calc(input.nextLong() - 1) + calc(input.nextLong()));
	}

	private static long calc(long n) {
		long result = n;
		for (long l = 2; l <= n; l <<= 1) {
			result += (n / l) * (l >> 1);
		}
		return result;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
