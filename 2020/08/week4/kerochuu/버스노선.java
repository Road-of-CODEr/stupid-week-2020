package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 17.                   
public class 버스노선 {

	static int N;
	static ArrayList<Integer>[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		init();

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			input(stoi(st.nextToken()), stoi(st.nextToken()));
		}

		System.out.println(calc());
	}

	private static int calc() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (map[i].size() == 1) {
				count++;
			}
		}
		return (count + 1) >> 1;
	}

	private static void input(int a, int b) {
		map[a].add(b);
		map[b].add(a);
	}

	private static void init() {
		map = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			map[i] = new ArrayList<>();
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
