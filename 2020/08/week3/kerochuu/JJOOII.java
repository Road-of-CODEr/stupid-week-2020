package baekjoon;

import java.io.*;
import java.util.ArrayList;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 16.                   
public class JJOOII {
	private static class Node {
		char c;
		int count;

		Node(char c, int count) {
			this.c = c;
			this.count = count;
		}
	}

	static ArrayList<Node> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] input = br.readLine().toCharArray();

		int count = 1;
		char c = input[0];
		for (int i = 1; i < input.length; i++) {
			if (input[i] == c) {
				count++;
			} else {
				list.add(new Node(c, count));
				c = input[i];
				count = 1;
			}
		}
		list.add(new Node(c, count));

		System.out.println(calc());
	}

	private static int calc() {
		int max = 0;
		for (int i = 0; i < list.size() - 2; i++) {
			if (list.get(i).c == 'K' && list.get(i + 1).c == 'R' && list.get(i + 2).c == 'C') {
				if (list.get(i + 1).count <= list.get(i).count && list.get(i + 1).count <= list.get(i + 2).count) {
					max = Math.max(max, list.get(i + 1).count);
				}
			}
		}
		return max * 3;
	}

}
