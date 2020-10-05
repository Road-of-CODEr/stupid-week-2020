import java.io.*;
import java.util.*;

public class bj_02733 {

	static int pointer, arr[] = new int[32768];
	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	static Stack<Integer> stack = new Stack<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb1, sb2;

		sb2 = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb1 = new StringBuilder();
			Arrays.fill(arr, 0);
			pointer = 0;
			map.clear();
			stack.clear();

			boolean exitFlag = false;
			int idx = 0;
			while (true) {
				String str = br.readLine();
				if (str.equals("end"))
					break;

				char[] temp = str.toCharArray();
				for (int i = 0, len = temp.length; i < len; i++) {
					if (temp[i] == '%') {
						break;
					} else if (temp[i] == '<' || temp[i] == '>' || temp[i] == '+' || temp[i] == '-' || temp[i] == '.'
							|| temp[i] == '[' || temp[i] == ']') {
						sb1.append(temp[i]);
						
						if (temp[i] == '[') {
							stack.push(idx);
						} else if (temp[i] == ']') {
							if (stack.size() > 0) {
								int left = stack.pop();
								map.put(left, idx);
								map.put(idx, left);
							} else {
								exitFlag = true;
								break;
							}
						}
						idx++;
					}
				}
			}
			sb2.append("PROGRAM #" + t + ":\n");
			if (exitFlag || stack.size() > 0)
				sb2.append("COMPILE ERROR\n");
			else
				sb2.append(brainFuck(sb1.toString()) + "\n");
		}
		System.out.println(sb2);
	}

	private static String brainFuck(String str) {
		StringBuilder sb = new StringBuilder();
		char[] temp = str.toCharArray();
		int idx = 0;
		
		while (true) {
			if (idx == str.length()) break;
			
			switch (temp[idx]) {
			case '<':
				if (pointer-- == 0)
					pointer = 32767;
				break;
			case '>':
				if (pointer++ == 32767)
					pointer = 0;
				break;
			case '+':
				if (arr[pointer]++ == 255)
					arr[pointer] = 0;
				break;
			case '-':
				if (arr[pointer]-- == 0)
					arr[pointer] = 255;
				break;
			case '.':
				sb.append((char) arr[pointer]);
				break;
			case '[':
				if (arr[pointer] == 0) {
					if (map.containsKey(idx))
						idx = map.get(idx);
					else
						idx = -1;
				}
				break;
			case ']':
				if (arr[pointer] != 0) {
					if (map.containsKey(idx))
						idx = map.get(idx);
					else
						idx = -1;
				}
				break;
			}
			
			if (idx == -1)
				return "COMPILE ERROR";
			else
				idx++;
		}
		return sb.toString();
	}
}