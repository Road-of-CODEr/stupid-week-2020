package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 8.                   
public class 오일러경로 {

	static int N;
	static int[] line;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		init();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = stoi(st.nextToken());
				line[i] += map[i][j];
				line[j] += map[i][j];
			}
		}

		for (int i = 1; i <= N; i++) {
			line[i] /= 2;
			if (line[i] % 2 == 1) {
				System.out.println(-1);
				return;
			}
		}

		dfs(1);
		System.out.println(sb.toString());
	}

	private static void dfs(int now) {
		for (int next = 1; next <= N; next++) {
			while (map[now][next] != 0) {
				map[now][next]--;
				map[next][now]--;
				dfs(next);
			}
		}
		sb.append(now + " ");
	}

	private static void init() {
		map = new int[N + 1][N + 1];
		line = new int[N + 1];
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
