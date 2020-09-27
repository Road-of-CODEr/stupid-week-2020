package baekjoon;

import java.io.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 9. 6.                   
public class 귀도반로썸은 {

	static int PC = 0, Adder = 0;
	static int[] Memory = new int[32];
	static boolean isEnd = false;
	static final int STA = 0, LDA = 1, BEQ = 2, NOP = 3, DEC = 4, INC = 5, JMP = 6, HLT = 7;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";

		while (true) {
			PC = 0;
			Adder = 0;
			for (int i = 0; i < 32; i++) {
				// if ((input = br.readLine()).length() < 2) { // 코드 실행용
				if ((input = br.readLine()) == null) { // 코드 제출용
					System.out.print(sb.toString());
					return;
				}
				Memory[i] = Integer.parseInt(input, 2);
			}

			while (calc(type(Memory[PC]), target(Memory[PC])));
		}
	}

	private static boolean calc(int type, int target) {
		PC = (PC + 1) % 32;
		switch (type) {
		case STA:
			Memory[target] = Adder;
			break;
		case LDA:
			Adder = Memory[target];
			break;
		case BEQ:
			if (Adder == 0) PC = target;
			break;
		case NOP:

			break;
		case DEC:
			Adder = (Adder + 255) % 256;
			break;
		case INC:
			Adder = (Adder + 1) % 256;
			break;
		case JMP:
			PC = target;
			break;
		case HLT:
			for (int i = 7; i >= 0; i--) {
				sb.append((Adder >> i) & 1);
			}
			sb.append("\n");
			return false;
		}
		return true;
	}

	private static int target(int input) {
		return input % 32;
	}

	private static int type(int input) {
		return input / 32;
	}
}
