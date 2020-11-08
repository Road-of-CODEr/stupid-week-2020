package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 11. 8.                   
public class 컨베이어벨트위의로봇 {
	private static class Robot {
		Node now;

		Robot(Node now) {
			this.now = now;
		}
	}

	private static class Node {
		int durability;
		boolean exit, exist;
		Node head, tail;

		Node() {}

		Node(int durability) {
			this.durability = durability;
			this.exit = false;
			this.exist = false;
		}
	}

	private static class KeroList {
		Node starting = new Node();
		int size;

		KeroList() {
			size = 0;
		}

		void add(Node n) {
			if (size == 0) {
				starting = n;
				starting.head = n;
				starting.tail = n;
			} else {
				Node last = starting.tail;
				last.head = n;
				n.tail = last;
				starting.tail = n;
			}
			size++;
		}

		void rotate() {
			Node n = starting;
			for (int i = 1; i < N; i++) {
				n = n.head;
			}
			n.exit = false;
			n.tail.exit = true;
			starting = starting.tail;
		}

		void print() {
			Node n = starting;
			for (int i = 0; i < size; i++) {
				System.out.format("%3d", n.durability);
				if (n.exit) {
					System.out.print("*");
				}

				n = n.head;
			}
			System.out.println();

			n = starting;
			for (int i = 0; i < size; i++) {
				if (n.exist) {
					System.out.format("%3c", '^');
				} else {
					System.out.format("   ");
				}
				n = n.head;
			}
			System.out.println();

//			n = starting;
//			for (int i = 0; i < size; i++) {
//				System.out.print(n.durability + " ");
//				n = n.tail;
//			}
//			System.out.println();
		}
	}

	static int N, M, index = 0, zero = 0;
	static KeroList keroList = new KeroList();
	static ArrayList<Robot> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N << 1; i++) {
			int durability = stoi(st.nextToken());
			keroList.add(new Node(durability));
			if (durability == 0) {
				zero++;
			}

		}
		Node n = keroList.starting.tail;
		n.head = keroList.starting;

		n = keroList.starting;
		for (int i = 1; i < N; i++) {
			n = n.head;
		}
		n.exit = true;

		int count = 0;
		while (zero < M) {
			count++;
			simulation();
		}

		System.out.println(count);
	}

	private static void simulation() {
		keroList.rotate();
//		keroList.print();
		Node n;
		for (int i = 0; i < list.size(); i++) {
			n = list.get(i).now;
			if (n.exit) {
				list.remove(i);
				n.exist = false;
				i--;
				continue;
			}
//			if (n.exit) {
//				list.remove(i);
//				n.exist = false;
//				System.out.println(n.durability + " " + "탈출~~11");
//				i--;
//				continue;
//			}
			if (!n.head.exist && n.head.durability >= 1) {
				n.head.durability--;
				if (n.head.durability == 0) {
					zero++;
				}
				if (!n.head.exit) {
					n.exist = false;
					list.get(i).now = n.head;
					n.head.exist = true;
				} else {
					n.exist = false;
					list.remove(i);
					i--;
				}

			}

		}

		n = keroList.starting;
		if (n.durability >= 1) {
			n.exist = true;
			list.add(new Robot(n));
			n.durability--;
			if (n.durability == 0) {
				zero++;
			}
		}

//		keroList.print();
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}

}
