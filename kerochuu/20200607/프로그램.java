import java.io.*;
import java.util.*;

// @address : blog.naver.com/kerochuu/221992095176
// @date : 2020. 6. 6.                   
public class 프로그램 {
	private static class SegTree {
		long[] data;
		int size;
		int height = 1;

		SegTree(int size) {
			while ((1 << height) < size) {
				height++;
			}
			this.size = 1 << (height + 1);
			data = new long[this.size];
		}

		long create(int now, int left, int right) {
			if (left == right) {
				return data[now] = num[left];
			}

			int next = now << 1;
			int mid = (left + right) >> 1;

			return data[now] = create(next, left, mid) + create(next + 1, mid + 1, right);
		}

		long query(int now, int left, int right, int L, int R) {
			if (L > right || R < left) {
				return 0;
			}

			if (L <= left && right <= R) {
				return data[now];
			}

			int next = now << 1;
			int mid = (left + right) >> 1;
			return query(next, left, mid, L, R) + query(next + 1, mid + 1, right, L, R);
		}

		void print() {
			int temp = 2;
			for (int i = 1; i < size; i++) {
				System.out.format("%2d ", data[i]);
				if (i == temp - 1) {
					System.out.println();
					temp <<= 1;
				}
			}
			System.out.println();
		}

	}

	static int N, M;
	static int[] num, add;
	static SegTree segTree;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		init();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			add[i] = stoi(st.nextToken());
		}
		add[M] = Integer.MAX_VALUE;
		Arrays.sort(add);

		int count = 0, temp = add[0];
		for (int i = 0; i <= M; i++) {
			if (temp == add[i]) {
				count++;
			} else {
				something(temp, count);
				count = 1;
				temp = add[i];
			}
		}

		segTree.create(1, 0, N - 1);
//		segTree.print();

		st = new StringTokenizer(br.readLine());
		temp = stoi(st.nextToken());
		for (int i = 0; i < temp; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(segTree.query(1, 0, N - 1, stoi(st.nextToken()), stoi(st.nextToken())) + "\n");
		}

		System.out.println(sb.toString());
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			System.out.print(num[i] + " ");
		}
		System.out.println();
	}

	private static void something(int jump, int count) {
		int i = 0;
		while (i < N) {
			num[i] = num[i] + count;
			i = i + jump;
		}
	}

	private static void init() {
		num = new int[N];
		add = new int[M + 1];
		segTree = new SegTree(N);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
