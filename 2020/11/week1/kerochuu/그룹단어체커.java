package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 1.                   
public class 그룹단어체커 {

	static int N;
	static boolean[] check = new boolean[26];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());

		int count = 0, idx = 0;
		for (int i = 0; i < N; i++, idx = 0) {
			init();
			char[] input = br.readLine().toCharArray();
			while (idx < input.length
					&& ((idx == 0) || (check[input[idx] - 'a'] ? input[idx] == input[idx - 1] : true))) {
				check[input[idx++] - 'a'] = true;
			}
			if (idx == input.length) {
				count++;
			}
		}
		System.out.println(count);
	}

	private static void init() {
		for (int i = 0; i < 26; i++) {
			check[i] = false;
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
