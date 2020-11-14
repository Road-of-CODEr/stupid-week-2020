package baekjoon;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 14.                   
public class 보석도둑 {
	private static class Node {
		int weight, value;

		Node(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}

	private static class KeroHeap { // minHeap
		int size;
		Node[] data;

		KeroHeap(int size) {
			data = new Node[size];
			this.size = 0;
			for (int i = 0; i < size; i++) {
				data[i] = new Node(-1, Integer.MAX_VALUE);
			}
		}

		boolean isEmpty() {
			return size == 0;
		}

		void add(Node n) {
			data[++size] = n;
			int idx = size;
			while (idx >> 1 != 0 && data[idx].value > data[idx >> 1].value) {
				swap(idx, idx >> 1);
				idx >>= 1;
			}
		}

		Node poll() {
			Node result = data[1];
			data[1] = data[size--];
			int now = 1, next;
			while (true) {
				next = now << 1;
				if (next < size && data[next].value < data[next + 1].value) {
					next++;
				}
				if (next > size || data[next].value < data[now].value) {
					break;
				}
				swap(next, now);
				now = next;
			}
			return result;
		}

		void swap(int a, int b) {
			Node temp = data[a];
			data[a] = data[b];
			data[b] = temp;
		}
	}

	static int N, M;
	static KeroHeap keroHeap;
	static TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		init();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			keroHeap.add(new Node(stoi(st.nextToken()), stoi(st.nextToken())));
		}

		int bag;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			bag = stoi(st.nextToken());
			if (tm.containsKey(bag)) {
				tm.put(bag, tm.get(bag) + 1);
			} else {
				tm.put(bag, 1);
			}
		}

		long allSum = 0;
		while (!keroHeap.isEmpty()) {
			Node n = keroHeap.poll();
			Entry<Integer, Integer> e = tm.ceilingEntry(n.weight);
			if (e != null) {
				if (e.getValue() == 1) {
					tm.remove(e.getKey());
				} else {
					tm.put(e.getKey(), e.getValue() - 1);
				}
				allSum += n.value;
			}
		}
		System.out.println(allSum);
	}

	private static void init() {
		keroHeap = new KeroHeap(N+1);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
