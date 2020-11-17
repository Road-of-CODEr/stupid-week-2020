package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 10. 10.                   
public class 저울 {

	static int N, M;
	static boolean[][] table;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		init();

		st = new StringTokenizer(br.readLine());
		M = stoi(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			memo(stoi(st.nextToken()), stoi(st.nextToken()));
		}

		System.out.print(floyd_warshall());
	}

	private static String floyd_warshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (k == i || i == j || j == k) continue;
					if (table[i][k] && table[k][j]) table[i][j] = true;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int count = -1;
			for (int j = 1; j <= N; j++) {
				if (!table[i][j] && !table[j][i]) {
					count++;
				}
			}
			sb.append(count + "\n");
		}
		return sb.toString();
	}

	private static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.format("%2d ", table[i][j] ? 1 : 0);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void memo(int a, int b) {
		table[a][b] = true;
	}

	private static void init() {
		table = new boolean[N + 1][N + 1];
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
