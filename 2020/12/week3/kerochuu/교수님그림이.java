package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 12. 20.                   
public class 교수님그림이 {

	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = stoi(st.nextToken()), enlarge = stoi(st.nextToken());

		for (int i = 0, temp; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < size; j++) {
				temp = stoi(st.nextToken());
				for (int k = 0; k < enlarge; k++) {
					sb.append(temp + " ");
				}
			}
			for (int k = 0; k < enlarge; k++) {
				result.append(sb.toString() + "\n");
			}
		}
		System.out.print(result.toString());
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
