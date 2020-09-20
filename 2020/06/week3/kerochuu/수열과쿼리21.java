package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 6. 16.                   
public class 수열과쿼리21 {
	private static class FenWick {
		long[] data;

		FenWick(int size) {
			data = new long[size + 1];
		}

		void update(int idx, long num) {
			while (idx <= N) {
				data[idx] += num;
				idx += (idx & -idx);
			}
		}

		long getNum(int idx) {
			long allSum = 0;
			while (idx > 0) {
				allSum += data[idx];
				idx -= (idx & -idx);
			}
			return allSum;
		}
	}

	static int N, M;
	static long[] data;
	static FenWick fenWick;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		init();

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			data[i] = stoi(st.nextToken());
		}

		for (int i = N; i >= 1; i--) {
			// 증가량 배열로 변환 후 penWick 업데이트
			data[i] = data[i] - data[i - 1];
			fenWick.update(i, data[i]);
		}

		st = new StringTokenizer(br.readLine());
		M = stoi(st.nextToken());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			if (stoi(st.nextToken()) == 1) {
				changeNum(stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
			} else {
				sb.append(fenWick.getNum(stoi(st.nextToken())) + "\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void changeNum(int left, int right, int value) {
		fenWick.update(left, value);
		fenWick.update(right + 1, -value);
	}

	private static void print(int[] data) {
		for (int i = 1; i <= N; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}

	private static void init() {
		data = new long[N + 1];
		fenWick = new FenWick(N + 1);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
