package baekjoon;

import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map.Entry;
import java.util.StringTokenizer;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 9.                   
public class ºóµµÁ¤·Ä {
	private static class Node implements Comparable<Node> {
		int num, appear, count;

		Node(int num, int appear, int count) {
			this.num = num;
			this.appear = appear;
			this.count = count;
		}

		@Override
		public int compareTo(Node n) {

			if (this.count != n.count) {
				return n.count - this.count;
			}
			return this.appear - n.appear;
		}
	}

	private static class Info {
		int appear, count;

		Info(int appear, int count) {
			this.appear = appear;
			this.count = count;
		}
	}

	static int N, M;
	static StringBuilder sb = new StringBuilder();
	static HashMap<Integer, Info> hm = new HashMap<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = stoi(st.nextToken());
			if (hm.containsKey(num)) {
				hm.put(num, new Info(hm.get(num).appear, hm.get(num).count + 1));
			} else {
				hm.put(num, new Info(i, 1));
			}
		}

		for (Entry<Integer, Info> e : hm.entrySet()) {
			pq.add(new Node(e.getKey(), e.getValue().appear, e.getValue().count));
		}

		while (!pq.isEmpty()) {
			Node n = pq.poll();
			for (int i = 0; i < n.count; i++) {
				sb.append(n.num + " ");
			}
		}
		System.out.println(sb.toString());
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
