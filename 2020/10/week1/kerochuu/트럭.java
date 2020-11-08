package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 10. 3.                   
public class 트럭 {

	private static class Node {
		int idx, time;

		Node(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}
	}

	static int N, maxWeight, Length, T = 0;
	static int[] truck;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		Length = stoi(st.nextToken());
		maxWeight = stoi(st.nextToken());

		truck = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			truck[i] = stoi(st.nextToken());
		}

		System.out.println(simulation());
	}

	private static int simulation() {
		int idx = 0, time = 0, weight = 0;
		Queue<Node> q = new LinkedList<Node>();

		while (!q.isEmpty() || idx != N) {
			if (!q.isEmpty() && time - q.peek().time == Length) {
				weight -= truck[q.poll().idx];
			}

			if (idx != N && weight + truck[idx] <= maxWeight) {
				q.add(new Node(idx, time));
				weight += truck[idx++];
			}
			time++;
		}
		return time;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
