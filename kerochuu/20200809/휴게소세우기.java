package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 9.                   
public class 휴게소세우기 {

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
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}});
		
		for (int i = 1; i < list.size(); i++) {
			pq.add(list.get(i) - list.get(i - 1) - 1);
		}
		
		for(int i = 0; i < M; i++) {
			int temp = pq.poll();
			if(temp %2 == 1) {
			}
			pq.add(pq.poll()>>1);
		}
		return pq.poll();
		
		
	}

	

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
