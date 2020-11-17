package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 8.                   
public class 버블소트 {

	private static class KeroList {
		int[] data;
		int size;

		KeroList(int N) {
			data = new int[N];
			size = 0;
		}

		void add(int num) {
			data[size++] = num;
		}

		void mergeSort(int start, int end) {
			if (start == end) {
				return;
			}
			int mid = (start + end) >> 1;
			mergeSort(start, mid);
			mergeSort(mid + 1, end);
			merge(start, mid, end);
		}

		void merge(int start, int mid, int end) {
			int left = start;
			int right = mid + 1;
			int index = 0;
			int[] temp = new int[end - start + 1];

			while (left <= mid && right <= end) {
				if (data[left] <= data[right]) {
					temp[index++] = data[left++];
				} else {
					temp[index++] = data[right++];
					count += mid + 1 - left;
				}
			}

			while (left <= mid) {
				temp[index++] = data[left++];
			}

			while (right <= end) {
				temp[index++] = data[right++];
			}

			for (int i = 0; i < temp.length; i++) {
				data[start + i] = temp[i];
			}
		}
	}

	static long count = 0;
	static KeroList keroList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = stoi(st.nextToken());
		keroList = new KeroList(N);
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			keroList.add(stoi(st.nextToken()));
		}
		keroList.mergeSort(0, N - 1);
		System.out.println(count);
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
