package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 12. 26.                   
public class 같이눈사람만들래 {

	static int N;
	static int[] snow;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		snow = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			snow[i] = stoi(st.nextToken());
		}

		Arrays.sort(snow);

		System.out.println(calc());
	}

	private static int calc() {
		int result = Integer.MAX_VALUE, anna, elsa, left, right;

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				elsa = snow[i] + snow[j];
				left = i;
				right = N - 1;

				while (true) {
					while (left == i || left == j) {
						left++;
					}
					while (right == i || right == j) {
						right--;
					}

					if (left >= right) {
						break;
					}

					anna = snow[left] + snow[right];
					result = Math.min(result, Math.abs(anna - elsa));
					if (elsa < anna) {
						right--;
					} else {
						left++;
					}
				}
			}
		}
		return result;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
