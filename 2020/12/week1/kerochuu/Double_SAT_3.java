package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 29.                   
public class Double_SAT_3 {

	static int N, M, IDX = 0, ID = 0;
	static ArrayList<Integer>[] list;
	static int[] visit, cycleIdx;
	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		init();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			calc_OR(stoi(st.nextToken()), stoi(st.nextToken()));
		}

		for (int i = 0; i < N << 1; i++) {
			if (visit[i] == -1) {
				scc(i);
			}
		}
		System.out.println(result());
	}

	private static int result() {
		for (int i = 0; i < N << 1; i += 2) {
			if (cycleIdx[i] == cycleIdx[i + 1]) {
				return 0;
			}
		}
		return 1;
	}

	private static int scc(int now) {
		int ret = visit[now] = IDX++;
		stack.add(now);
		for (int next : list[now]) {
			if (visit[next] == -1) {
				ret = Math.min(ret, scc(next));
			} else if (cycleIdx[next] == -1) {
				ret = Math.min(ret, visit[next]);
			}
		}

		if (ret == visit[now]) {
			int temp;
			do {
				cycleIdx[temp = stack.pop()] = ID;
			} while (temp != now);
			ID++;
		}
		return ret;
	}

	private static void calc_OR(int a, int b) {
		list[(a = convert(a)) ^ 1].add(b);
		list[(b = convert(b)) ^ 1].add(a);
	}

	private static int convert(int x) {
		return x > 0 ? (x - 1) << 1 : (-x << 1) - 1;
	}

	private static void init() {
		list = new ArrayList[N << 1];
		visit = new int[N << 1];
		cycleIdx = new int[N << 1];
		for (int i = 0; i < N << 1; i++) {
			list[i] = new ArrayList<>();
			visit[i] = -1;
			cycleIdx[i] = -1;
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
