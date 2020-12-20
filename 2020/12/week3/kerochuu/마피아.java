package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 12. 20.                   
public class 마피아 {

	static int N, mafia, result = -1;
	static int[][] R;
	static int[] point;
	static boolean[] isDead;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());

		init();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			point[i] = stoi(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				R[i][j] = stoi(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		mafia = stoi(st.nextToken());
		
		recursive(0, 0);
		System.out.println(result);
	}

	private static void recursive(int depth, int count) {
		if (isDead[mafia] || depth == N-1) {
			result = Math.max(result, count);
			return;
		}

		if (depth % 2 == 0) {
			for (int i = 0; i < N; i++) {
				if (isDead[i]) continue;
				isDead[i] = true;
				calc(i, 1);
				recursive(depth + 1, count + 1);
				calc(i, -1);
				isDead[i] = false;
			}
		} else {
			int idx = findMax();
			isDead[idx] = true;
			recursive(depth + 1, count);
			isDead[idx] = false;
		}
	}

	private static void calc(int idx, int type) {
		for (int j = 0; j < N; j++) {
			if (!isDead[j]) point[j] += R[idx][j] * type;
		}
	}

	private static int findMax() {
		int idx = -1, maxPoint = 0;
		for (int i = 0; i < N; i++) {
			if (!isDead[i] && maxPoint < point[i]) {
				maxPoint = point[i];
				idx = i;
			}
		}
		return idx;
	}

	private static void init() {
		R = new int[N][N];
		point = new int[N];
		isDead = new boolean[N];
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
