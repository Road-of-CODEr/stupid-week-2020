package baekjoon;

import java.io.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 12.                   
public class brainFuck {
	private static class KeroList {
		char[] data;
		int[] jumpIdx;
		int size, capacity = 8;
		KeroStack stack;

		KeroList() {
			stack = new KeroStack();
			data = new char[capacity];
			jumpIdx = new int[capacity];
			size = 0;
		}

		void init() {
			for (int i = 0; i < size; i++) {
				jumpIdx[i] = 0;
			}
			stack.init();
			size = 0;
		}

		void reallocate() {
			capacity <<= 1;
			char[] temp1 = new char[capacity];
			int[] temp2 = new int[capacity];
			for (int i = 0; i < size; i++) {
				temp1[i] = data[i];
				temp2[i] = jumpIdx[i];
			}
			data = temp1;
			jumpIdx = temp2;
		}

		boolean add(char c) {
			if (size == capacity) {
				reallocate();
			}
			if (c == '[') { // size 값이 논리적으로 index가 됨
				stack.push(size);
			} else if (c == ']') {
				if (stack.isEmpty()) {
					return false;
				}
				int target = stack.pop();
				jumpIdx[size] = target;
				jumpIdx[target] = size;
			}

			data[size++] = c;
			return true;
		}

		char get(int idx) {
			return data[idx];
		}

		int size() {
			return this.size;
		}
	}

	private static class KeroStack {
		int[] data;
		int size, capacity = 8;

		KeroStack() {
			data = new int[capacity];
			size = 0;
		}

		void init() {
			size = 0;
		}

		void reallocate() {
			capacity <<= 1;
			int[] temp = new int[capacity];
			for (int i = 0; i < size; i++) {
				temp[i] = data[i];
			}
			data = temp;
		}

		void push(int idx) {
			if (size == capacity) {
				reallocate();
			}
			data[size++] = idx;
		}

		int pop() {
			return data[--size];
		}

		int size() {
			return this.size;
		}

		boolean isEmpty() {
			return size == 0;
		}
	}

	static boolean compileError = false;
	static int arrSize = 32768, dataSize = 256, pointer = 0;
	static KeroList input = new KeroList();
	static int[] arr = new int[arrSize];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tnum = stoi(br.readLine());

		String temp;
		for (int t = 1; t <= tnum; t++) {
			while (!(temp = br.readLine()).equals("end")) {
				for (int i = 0; i < temp.length(); i++) {
					if (isWrong(temp.charAt(i))) {
						break;
					}
				}
			}

			sb.append("PROGRAM #" + t + ":\n");
			if (compileError || input.stack.size() != 0) {
				sb.append("COMPILE ERROR");
			} else {
				calc();
			}
			sb.append("\n");
			init();
		}
		System.out.print(sb.toString());
	}

	private static void calc() {
		int inputIdx = 0;
		while (inputIdx != input.size()) {
			switch (input.get(inputIdx)) {
			case '>':
				pointer = (pointer + 1) % arrSize;
				break;
			case '<':
				pointer = (pointer + arrSize - 1) % arrSize;
				break;
			case '+':
				arr[pointer] = (arr[pointer] + 1) % dataSize;
				break;
			case '-':
				arr[pointer] = (arr[pointer] + dataSize - 1) % dataSize;
				break;
			case '.':
				sb.append((char) arr[pointer]);
				break;
			case '[':
				if (arr[pointer] == 0) {
					inputIdx = input.jumpIdx[inputIdx];
				}
				break;
			case ']':
				if (arr[pointer] != 0) {
					inputIdx = input.jumpIdx[inputIdx];
				}
				break;
			}
			inputIdx++;
		}
	}

	private static boolean isWrong(char c) {
		switch (c) {
		case '%':
			return true;
		case '>':
		case '<':
		case '+':
		case '-':
		case '.':
		case '[':
		case ']':
			if (!input.add(c)) {
				return compileError = true;
			}
		}
		return false;
	}

	private static void init() {
		for (int i = 0; i < arrSize; i++) {
			arr[i] = 0;
		}
		pointer = 0;
		compileError = false;
		input.init();
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}
