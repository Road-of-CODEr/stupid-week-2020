package baekjoon;

import java.io.*;
import java.util.*;

//  @author : blog.naver.com/kerochuu 
//  @date : 2020. 9. 30.                   
public class 학회원 {

	static int N, count;
	static String target;

	static HashMap<String, ArrayList<String>> hm = new HashMap<>();
	static HashSet<String> visit = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while ((N = stoi(br.readLine())) != 0) {
//			keroMap.init();
			hm.clear();
			visit.clear();
			count = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), ":|,|.");

				String key = st.nextToken();
				if (i == 0) {
					target = key;
				}
				if (!hm.containsKey(key)) {
					hm.put(key, new ArrayList<>());
				}
				while (st.hasMoreTokens()) {
					hm.get(key).add(st.nextToken());
				}
			}
			visit.add(target);
			System.out.println(recursive(target, 0));
		}
	}

	private static int recursive(String key, int count) {
		if (hm.containsKey(key)) {
			for (String child : hm.get(key)) {
				if (!visit.contains(child)) {
					visit.add(child);
					count += recursive(child, hm.get(child) == null ? 1 : 0);
				}
			}
		}
		return count;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}