package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 8.                   
public class 게임게발 {

	static int N;
	static ArrayList<Integer>[] list;

	static int[] childNum, time, result;
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		N = input.nextInt();

		list = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		childNum = new int[N + 1];
		result = new int[N + 1];
		time = new int[N + 1];
		int idx;
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
			time[i] = input.nextInt();
			idx = input.nextInt();
			while (idx != -1) {
				list[idx].add(i);
				childNum[i]++;
				idx = input.nextInt();
			}
		}
		// print();
		topological();
		for (int i = 1; i <= N; i++) {
			System.out.println(result[i]);
		}
	}

	private static void topological() {
		init();
		while (!q.isEmpty()) {
			int now = q.poll();

			for (int i = 0; i < list[now].size(); i++) {
				int next = list[now].get(i);
				if (--childNum[next] == 0) {
					q.add(next);
					result[next] = Math.max(result[next], result[now] + time[next]);
				}
			}
		}
	}

	private static void init() {
		for (int i = 1; i <= N; i++) {
			if (childNum[i] == 0) {
				q.add(i);
				result[i] = time[i];
			}
		}
	}
}
