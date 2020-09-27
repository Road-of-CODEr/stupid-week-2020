package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 2.                   
public class 가운데를말해요 {

	private static class KeroHeap {
		int size;
		int[] heap;
		boolean isMaxHeap;

		KeroHeap(int N, boolean isMaxHeap) {
			heap = new int[N + 1];
			size = 0;
			this.isMaxHeap = isMaxHeap;
			for (int i = 0; i < N; i++) { // 음수까지 대응가능
				heap[i] = Integer.MIN_VALUE;
			}
		}

		boolean isEmpty() {
			return size == 0;
		}

		void add(int num) {
			heap[++size] = num;
			int idx = size;
			while (idx >> 1 != 0 && isMaxHeap ? heap[idx] > heap[idx >> 1] : heap[idx] < heap[idx >> 1]) {
				swap(idx, idx >> 1);
				idx >>= 1;
			}
		}

		int poll() {
			int result = heap[1];
			heap[1] = heap[size--];
			int parent = 1;
			int child;
			while (true) {
				child = parent << 1;
				if (child < size && (isMaxHeap ? heap[child] < heap[child + 1] : heap[child] > heap[child + 1])) {
					child++;
				}
				if (child > size || (isMaxHeap ? heap[child] < heap[parent] : heap[child] > heap[parent])) {
					break;
				}
				swap(child, parent);
				parent = child;
			}
			return result;
		}

		int peek() {
			return heap[1];
		}

		int size() {
			return size;
		}

		void swap(int a, int b) {
			int temp = heap[a];
			heap[a] = heap[b];
			heap[b] = temp;
		}
	}

	static int N;
	static KeroHeap left, right;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		init();

		for (int i = 0; i < N; i++) {
			int num = input.nextInt();
			if (left.isEmpty() || right.size() == left.size()) {
				left.add(num);
			} else {
				right.add(num);
			}
			if (!right.isEmpty() && !left.isEmpty() && right.peek() < left.peek()) {
				if (!right.isEmpty()) {
					int temp1 = right.poll();
					left.add(temp1);
				}
				if (!left.isEmpty()) {
					int temp2 = left.poll();
					right.add(temp2);
				}
			}
			sb.append(left.peek() + "\n");
		}
		System.out.print(sb.toString());
	}

	private static void init() {
		left = new KeroHeap(50001, true);
		right = new KeroHeap(50001, false);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}

}
