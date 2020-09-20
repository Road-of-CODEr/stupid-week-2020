package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 7. 4.                   
public class 이진트리 {

	static int depth, size, result = 0;
	static int[] E, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		depth = stoi(st.nextToken());

		init(size = (depthToSize() + 2));

		st = new StringTokenizer(br.readLine());
		for (int i = 2; i < size; i++) {
			result += (E[i] = stoi(st.nextToken()));
		}

		recursive_findMax(1);
		recursive_findResult(1);
		System.out.println(result);
	}

	private static void init(int size) {
		E = new int[size];
		max = new int[size];
	}

	private static void recursive_findMax(int now) {
		int left = now << 1;
		int right = left + 1;

		if (right <= size) {
			recursive_findMax(left);
			recursive_findMax(right);
			max[now] = E[now] + Math.max(max[left], max[right]);
		} else {
			max[now] = E[now];
		}
	}

	private static void recursive_findResult(int now) {
		int left = now << 1;
		int right = left + 1;

		if (right <= size) {
			result += ((max[now] - E[now]) << 1) - max[left] - max[right];
			recursive_findResult(left);
			recursive_findResult(right);
		}
	}

	private static int depthToSize() {
		int count = 0;
		for (int temp = 1; temp <= depth; temp++) {
			count += 1 << temp;
		}
		return count;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
