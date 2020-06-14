package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 6. 7.                   
public class 구간합구하기 {
	private static class PenWick {
		long[] data;

		PenWick(int size) {
			data = new long[size];
		}

		void update(int idx, long num) {
			while (idx < data.length) {
				data[idx] += num;
				idx += (idx & -idx);
			}
		}

		long sum(int idx) {
			long allSum = 0;
			while (idx > 0) {
				allSum += data[idx];
				idx -= (idx & -idx);
			}
			return allSum;
		}
	}

	static int N, M, K;
	static long[] list;
	static PenWick penWick;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());

		list = new long[N + 1];
		penWick = new PenWick(N + 1);

		for (int i = 1; i <= N; i++) {
			list[i] = stol(br.readLine());
			penWick.update(i, list[i]);
		}

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			if (stoi(st.nextToken()) == 1) {
				int idx = stoi(st.nextToken());
				long input = stol(st.nextToken());
				penWick.update(idx, input - list[idx]);
				list[idx] = input;
			} else {
				int left = stoi(st.nextToken());
				int right = stoi(st.nextToken());
				sb.append((penWick.sum(right) - penWick.sum(left - 1)) + "\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}

	private static long stol(String input) {
		return Long.parseLong(input);
	}
}
