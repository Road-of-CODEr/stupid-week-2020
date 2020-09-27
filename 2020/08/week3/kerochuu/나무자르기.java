package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 9.       
public class 나무자르기 {
	
	static int treeNum, wantLength, max = -1;
	static int[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		treeNum = stoi(st.nextToken());
		wantLength = stoi(st.nextToken());

		list = new int[treeNum];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < treeNum; i++) {
			list[i] = stoi(st.nextToken());
			max = Math.max(max, list[i]);
		}

		System.out.println(binarySearch(0, max));
	}

	private static int binarySearch(int left, int right) {
		while (left <= right) {
			int mid = (left + right) >> 1;
			if (isPossible(mid)) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return right;
	}

	private static boolean isPossible(int length) {
		long allSum = 0;
		for (int i = 0; i < treeNum; i++) {
			if (list[i] > length) {
				allSum += list[i] - length;
			}
		}
		return allSum >= wantLength;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}