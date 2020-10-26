package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 10. 18.                   

public class 몇번나누어떨어질까 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		System.out.println(-calc(stol(st.nextToken()) - 1) + calc(stol(st.nextToken())));
	}

	private static long calc(long n) {
		long result = n;
		for (long l = 2; l <= n; l <<= 1) {
			result += (n / l) * (l >> 1);
		}
		return result;
	}

	private static long stol(String input) {
		return Long.parseLong(input);
	}
}
