package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 9.                   
public class 휴게소세우기_binary {

	static int N, M, L;
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		L = stoi(st.nextToken());
		
		list.add(0);
		list.add(L);
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			list.add(stoi(st.nextToken()));
		}
		Collections.sort(list);
		
		System.out.println(binary());
	}

	private static int binary() {
		int left = 0, right = L;

		while (left <= right) {
			int mid = (left + right) >> 1;
			if (isPossible(mid)) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}

	private static boolean isPossible(int length) {
		int count = 0;
		for (int i = 1; i < list.size(); i++) {
			count += (list.get(i) - list.get(i - 1) - 1) / length;
		}
		return count > M;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
