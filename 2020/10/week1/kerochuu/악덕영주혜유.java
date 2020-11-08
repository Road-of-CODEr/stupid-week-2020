package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 0. 3.                   
public class 악덕영주혜유 {
	private static class Node {
		int start, dest;
		long cost;

		Node(int start, int dest, long cost) {
			this.start = start;
			this.dest = dest;
			this.cost = cost;
		}

		Node(int dest, long cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}

	private static class KeroList {
		Node[] data;
		int size, capacity = 8;

		KeroList() {
			data = new Node[capacity];
			size = 0;
		}

		void reallocate() {
			Node[] temp = new Node[capacity <<= 1];
			for (int i = 0; i < size; i++) {
				temp[i] = data[i];
			}
			data = temp;
		}

		void add(Node n) {
			if (size == capacity) {
				reallocate();
			}
			data[size++] = n;
		}

		Node get(int idx) {
			return data[idx];
		}
	}

	private static class KeroHeap { // minHeap
		int size;
		Node[] data;

		KeroHeap(int size) {
			data = new Node[size];
			this.size = 0;
			for (int i = 0; i < size; i++) {
				data[i] = new Node(-1, Integer.MIN_VALUE);
			}
		}

		boolean isEmpty() {
			return size == 0;
		}

		void add(Node n) {
			data[++size] = n;
			int idx = size;
			while (idx >> 1 != 0 && data[idx].cost < data[idx >> 1].cost) {
				swap(idx, idx >> 1);
				idx >>= 1;
			}
		}

		Node poll() {
			Node result = data[1];
			data[1] = data[size--];
			int parent = 1, child;
			while (true) {
				child = parent << 1;
				if (child < size && data[child].cost > data[child + 1].cost) {
					child++;
				}
				if (child > size || data[child].cost > data[parent].cost) {
					break;
				}
				swap(child, parent);
				parent = child;
			}
			return result;
		}

		void swap(int a, int b) {
			Node temp = data[a];
			data[a] = data[b];
			data[b] = temp;
		}

	}

	static int V, E, starting = 0;
	static long maxLength = -1, totalLength = 0;
	static int[] parents;
	static boolean[] visit;
	static KeroHeap keroHeap;
	static KeroList[] keroList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = stoi(st.nextToken());
		E = stoi(st.nextToken());

		init();

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			keroHeap.add(new Node(stoi(st.nextToken()), stoi(st.nextToken()), (long) stoi(st.nextToken())));
		}

		kruscal();
		findMaxLength();

		System.out.println(totalLength + "\n" + maxLength);
	}

	private static void kruscal() {
		while (!keroHeap.isEmpty()) {
			Node n = keroHeap.poll();
			if (union(n.start, n.dest)) {
				keroList[n.start].add(new Node(n.dest, n.cost));
				keroList[n.dest].add(new Node(n.start, n.cost));
				totalLength += n.cost;
			}
		}
	}

	private static void findMaxLength() {
		visit = new boolean[V];
		visit[starting] = true;
		dfs(starting, 0);

		visit = new boolean[V];
		visit[starting] = true;
		dfs(starting, 0);
	}

	private static void dfs(int idx, long allSum) {
		if (maxLength < allSum) {
			maxLength = allSum;
			starting = idx;
		}

		for (int i = 0; i < keroList[idx].size; i++) {
			Node n = keroList[idx].get(i);
			if (!visit[n.dest]) {
				visit[n.dest] = true;
				dfs(n.dest, allSum + n.cost);
			}
		}
	}

	private static void init() {
		parents = new int[V];
		keroList = new KeroList[V];
		keroHeap = new KeroHeap(E + 1);
		for (int i = 0; i < V; i++) {
			parents[i] = -1;
			keroList[i] = new KeroList();
		}
	}

	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			parents[b] = a;
			return true;
		}
		return false;
	}

	private static int find(int a) {
		if (parents[a] == -1) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
