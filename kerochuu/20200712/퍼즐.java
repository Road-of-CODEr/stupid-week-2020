package baekjoon;

import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 7. 12.                   
public class 퍼즐 {
	private static class Node {
		long map;
		int step, idx;

		Node(long map, int step, int idx) {
			this.map = map;
			this.step = step;
			this.idx = idx;
		}
	}

	static Queue<Node> q = new LinkedList<>();
	static HashSet<Long> visit = new HashSet<>();
	static int[] dx = { -3, 3, -1, 1 };

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		long map = 0;
		int idx = -1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int num = input.nextInt();
				if (num == 0) {
					idx = i * 3 + j;
					map = map * 10 + 9;
				} else {
					map = map * 10 + num;
				}
			}
		}
		visit.add(map);
		q.add(new Node(map, 1, idx));

		System.out.println(map == 123456789L ? 0 : bfs());
	}

	private static int bfs() {
		int nextIdx;
		long nextMap;

		while (!q.isEmpty()) {
			Node n = q.poll();

			for (int i = 0; i < 4; i++) {
				if ((nextIdx = isPossible(n.idx, i)) != -1 && !visit.contains(nextMap = swap(n.map, n.idx, nextIdx))) {
					if (nextMap == 123456789L) {
						return n.step;
					}
					visit.add(nextMap);
					q.add(new Node(nextMap, n.step + 1, nextIdx));
				}
			}
		}
		return -1;
	}

	private static long swap(long map, int a, int b) {
		long l = map;
		int[] temp = new int[9];
		for (int i = 8; i >= 0; i--) {
			temp[i] = (int) (l % 10);
			l /= 10;
		}
		temp[a] ^= temp[b];
		temp[b] ^= temp[a];
		temp[a] ^= temp[b];

		l = 0;
		for (int i = 0; i < 9; i++) {
			l = l * 10 + temp[i];
		}
		return l;
	}

	private static int isPossible(int idx, int dir) {
		int next = idx + dx[dir];

		switch (dir) {
		case 0:
			return next >= 0 ? next : -1;
		case 1:
			return next < 9 ? next : -1;
		case 2:
		case 3:
			return next / 3 == idx / 3 ? next : -1;
		}
		System.out.println("이거나올일없음");
		return -1;
	}
}
