package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 6. 13.                   
public class À½ÁÖÄÚµù_ÆæÀ¨ {
	private static class Penwick {
		int[] data;

		Penwick() {
			data = new int[100001];
		}

		void init(int size) {
			for (int i = 1; i <= size; i++) {
				data[i] = 0;
				input[i] = 0;
			}
		}

		void update(int idx, long num) {
			while (idx <= N) {
				data[idx] += num;
				idx += (idx & -idx);
			}
		}

		int sum(int idx) {
			int allSum = 0;
			while (idx > 0) {
				allSum += data[idx];
				idx -= (idx & -idx);
			}
			return allSum;
		}

	}

	static int N, M;
	final static int ZERO = 100000;
	static Penwick tree = new Penwick();
	static int[] input = new int[100001];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		while (true) {
			String s = br.readLine();
			if (s == null) {
				break;
			}
			st = new StringTokenizer(s);

			N = stoi(st.nextToken());
			M = stoi(st.nextToken());
			tree.init(N);
			sb = new StringBuilder();

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				int num = stoi(st.nextToken());
				input[i] = num;
				tree.update(i, getSimple(num));
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				char type = st.nextToken().charAt(0);
				if (type == 'C') {
					int x = stoi(st.nextToken());
					int y = stoi(st.nextToken());
					long diff = (getSimple(y) - getSimple(input[x]));
					input[x] = y;
					tree.update(x, diff);
				} else {
					int x = stoi(st.nextToken());
					int y = stoi(st.nextToken());
					long allSum = tree.sum(y) - tree.sum(x - 1);
					sb.append(allSum >= ZERO ? "0" : allSum % 2 == 0 ? "+" : "-");
				}
			}
			System.out.println(sb.toString());
		}
	}

	private static void print(int[] input) {
		for (int i = 1; i <= N; i++) {
			if (input[i] >= ZERO) {
				System.out.print("0 ");
			} else if (input[i] == 1) {
				System.out.print("- ");
			} else {
				System.out.print("+ ");
			}
		}
		System.out.println();
	}

	private static int getSimple(int num) {
		return num == 0 ? ZERO : num > 0 ? 0 : 1;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
