package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 2.                   
public class ∏÷∆º≈«Ω∫ƒ…¡Ï∏µ {

	static int N, M;
	static int[] item;
	static HashSet<Integer> octoFoot = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		item = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			item[i] = stoi(st.nextToken());
		}

		System.out.println(calc());
	}

	private static int calc() {
		int count = 0;
		for (int i = 0; i < M; i++) {
			if (octoFoot.contains(item[i]) || isPossible(item[i])) {
				continue;
			}

			int max = -1, pick = -1;
			for (int num : octoFoot) {
				int temp = 0;
				for (int j = i + 1; j < M; j++) {
					if (num == item[j]) {
						break;
					}
					temp++;
				}
				if (temp > max) {
					pick = num;
					max = temp;
				}
			}
			octoFoot.remove(pick);
			octoFoot.add(item[i]);
			count++;
		}
		return count;
	}

	private static boolean isPossible(int item) {
		if (octoFoot.size() < N) {
			octoFoot.add(item);
			return true;
		}
		return false;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
