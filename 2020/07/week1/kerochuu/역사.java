package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 7. 2.                   
public class ¿ª»ç {

	static int N, M;
	static int[][] history;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		init();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int past = stoi(st.nextToken());
			int future = stoi(st.nextToken());
			history[past][future] = -1;
			history[future][past] = 1;
		}

		floyd();

		st = new StringTokenizer(br.readLine());
		M = stoi(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(history[stoi(st.nextToken())][stoi(st.nextToken())] + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (k != i && i != j && j != k && history[i][j] == 0) {
						if (history[i][k] * history[k][j] == 1) {
							history[i][j] = history[i][k];
						}
					}
				}
			}
		}
	}

	private static void init() {
		history = new int[N + 1][N + 1];
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
