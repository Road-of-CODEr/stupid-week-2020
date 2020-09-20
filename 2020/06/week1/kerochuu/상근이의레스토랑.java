import java.io.*;
import java.util.*;

// @address : blog.naver.com/kerochuu/221992416399
// @date : 2020. 6. 5.                   
public class 상근이의레스토랑 {
	private static class Node implements Comparable<Node> {
		int first, normal;

		Node(int first, int normal) {
			this.first = first;
			this.normal = normal;
		}

		@Override
		public int compareTo(Node n) {
			return this.normal - n.normal;
		}
	}

	static int N, idx, num[];
	static long allSum = 0;
	static Node[] data;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		init();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			data[i] = new Node(stoi(st.nextToken()), stoi(st.nextToken()));
		}

		Arrays.sort(data);

		num[N] = Integer.MAX_VALUE;

		for (int i = N - 1; i >= 0; i--) {
			num[i] = Math.min(data[i].first, num[i + 1]);
		}

		StringBuilder sb = new StringBuilder();
		long temp;
		for (int i = 0; i < N; i++) {
			if (data[idx].normal - data[idx].first < data[i].normal - data[i].first) {
				idx = i;
			}
			allSum += data[i].normal;
			temp = Math.min(allSum - data[idx].normal + data[idx].first, allSum - data[i].normal + num[i]);
			sb.append(temp + "\n");
		}
		System.out.println(sb.toString());
	}

	private static void init() {
		num = new int[N + 1];
		data = new Node[N];
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
