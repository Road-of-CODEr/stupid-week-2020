package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 7. 26.                   
public class Ä«µå¼¯±â {

	static int N;
	static int[] temp, order, shuffle, origin;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		order = new int[N];
		shuffle = new int[N];
		origin = new int[N];
		temp = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			origin[i] = order[i] = stoi(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			shuffle[i] = stoi(st.nextToken());
		}

		System.out.println(findSortCount());
	}

	private static int findSortCount() {
		int count = 0;
		while (!isSame()) {
			count++;
			shuffle();
			if (isCycle()) {
				return -1;
			}
		}
		return count;
	}

	private static void shuffle() {
		for (int i = 0; i < N; i++) {
			temp[i] = order[i];
		}
		for (int i = 0; i < N; i++) {
			order[shuffle[i]] = temp[i];
		}
	}

	private static boolean isCycle() {
		for (int i = 0; i < N; i++) {
			if (origin[i] != order[i]) {
				return false;
			}
		}
		return true;
	}

	private static boolean isSame() {
		for (int i = 0; i < N; i++) {
			if (order[i] != (i % 3)) {
				return false;
			}
		}
		return true;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
