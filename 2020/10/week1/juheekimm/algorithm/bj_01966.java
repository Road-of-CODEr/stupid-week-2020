import java.io.*;
import java.util.*;

public class bj_01966 {
	static int N, M;
	static LinkedList<Integer> list = new LinkedList<Integer>();
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			list.clear();
			pq.clear();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int temp = Integer.parseInt(st.nextToken());
				list.add(temp);
				pq.add(temp);
			}
			sb.append(simulation() + "\n");
		}
		System.out.println(sb);
	}

	private static int simulation() {
		int count = 0;
		while (!list.isEmpty()) {
			if (pq.peek() > list.get(0)) {
				list.add(list.removeFirst());
				if (--M < 0) M = list.size() - 1;
			} else {
				pq.poll();
				list.removeFirst();
				count++;
				if (--M < 0) return count;
			}
		}
		return 0;
	}
}