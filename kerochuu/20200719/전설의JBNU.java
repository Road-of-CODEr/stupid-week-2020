package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 7. 18.                   
public class Àü¼³ÀÇJBNU {

	static int N, M, K;
	static TreeMap<Integer, Integer> tm = new TreeMap<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());

		tm.put(-2000000000, -1);
		tm.put(2000000000, -1);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			tm.put(stoi(st.nextToken()), stoi(st.nextToken()));
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int order = stoi(st.nextToken());
			switch (order) {
			case 1:
				tm.put(stoi(st.nextToken()), stoi(st.nextToken()));
				break;
			case 2:
				changeValue(stoi(st.nextToken()), stoi(st.nextToken()));
				break;
			case 3:
				findKey(stoi(st.nextToken()));
				break;
			}
		}
		System.out.print(sb.toString());
	}

	private static void changeValue(int key, int value) {
		int rightKey = tm.ceilingKey(key);
		int leftKey = tm.floorKey(key);

		if (tm.get(rightKey) == tm.get(leftKey)) {
			tm.put(key, value);
		} else if (rightKey - key < key - leftKey && K >= rightKey - key) {
			tm.put(rightKey, value);
		} else if (rightKey - key > key - leftKey && K >= key - leftKey) {
			tm.put(leftKey, value);
		}
	}

	private static void findKey(int key) {
		int rightKey = tm.ceilingKey(key);
		int leftKey = tm.floorKey(key);
		
		if (tm.get(rightKey) == tm.get(leftKey)) {
			sb.append(tm.get(key) + "\n");
		} else if (rightKey - key == key - leftKey && K >= rightKey - key) {
			sb.append("?\n");
		} else if (rightKey - key < key - leftKey && K >= rightKey - key) {
			sb.append(tm.get(rightKey) + "\n");
		} else if (rightKey - key > key - leftKey && K >= key - leftKey) {
			sb.append(tm.get(leftKey) + "\n");
		} else {
			sb.append("-1\n");
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
