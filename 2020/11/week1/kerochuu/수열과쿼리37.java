package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 1.                   
public class 수열과쿼리37 {

	private static class FenWick {
		int[] data;

		FenWick(int size) {
			data = new int[size + 1];
		}

		void update(int idx, int num) {
			while (idx <= N) {
				data[idx] += num;
				idx += (idx & -idx);
			}
		}

		int getOddNum(int idx) {
			int allSum = 0;
			while (idx > 0) {
				allSum += data[idx];
				idx -= (idx & -idx);
			}
			return allSum;
		}
	}

	static int N, M, type;
	static int[] input;
	static FenWick fenWick;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		fenWick = new FenWick(N);
		input = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			fenWick.update(i, input[i] = (stoi(st.nextToken()) % 2));
		}

		st = new StringTokenizer(br.readLine());
		M = stoi(st.nextToken());
		int oddNum, left = -1, right = -1;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			switch (type = stoi(st.nextToken())) {
			case 1:
				if (input[left = stoi(st.nextToken())] != (right = stoi(st.nextToken()) % 2)) {
					fenWick.update(left, right == 0 ? -1 : 1);
					input[left] = right;
				}
				break;
			default:
				oddNum = (-fenWick.getOddNum(left = stoi(st.nextToken()) - 1)
						+ fenWick.getOddNum(right = stoi(st.nextToken())));
				bw.write(String.valueOf(type == 3 ? oddNum : (right - left - oddNum)));
				bw.newLine();
				break;
			}
		}
		bw.flush();
		bw.close();
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
