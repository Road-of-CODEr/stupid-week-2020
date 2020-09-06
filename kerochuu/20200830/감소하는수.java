package baekjoon;

import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 8. 30.                   
public class 감소하는수 {

	static int target, count = -1, D = 1;
	static int[] list = new int[12];

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Arrays.fill(list, Integer.MIN_VALUE);
		list[0] = 10;
		target = input.nextInt();

		while (count != target && ++D != 12) {
			backTracking(1);
		}

		if (list[D - 1] == Integer.MIN_VALUE) {
			System.out.print(-1);
		} else {
			for (int i = 1; i < D; i++) {
				System.out.print(list[i]);
			}
		}
		System.out.println();
	}

	private static boolean backTracking(int depth) {
		if (depth == D) {
			for (int i = 2; i < depth; i++) {
				if (list[i] >= list[i - 1]) {
					return false;
				}
			}
			return ++count == target;
		}

		for (int i = 0; i < list[depth - 1]; i++) {
			list[depth] = i;
			if (backTracking(depth + 1)) {
				return true;
			}
		}
		return false;
	}
}
