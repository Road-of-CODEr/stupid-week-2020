import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int a, b, cost;
        Node (int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, M, totalCost, parents[];
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = stoi(br.readLine());
        M = stoi(br.readLine());
        parents = new int[N];
        Arrays.fill(parents, -1);

        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = stoi(st.nextToken()) - 1;
            b = stoi(st.nextToken()) - 1;
            c = stoi(st.nextToken());

            if (a == b) continue;
            pq.add(new Node(a, b, c));
        }

        kruskal();
        System.out.println(totalCost);
    }

    private static void kruskal() {
        int count = 0;
        while (!pq.isEmpty() && count < N - 1) {
            Node temp = pq.poll();
            if (union(temp.a, temp.b)) {
                totalCost += temp.cost;
                count++;
            }
        }
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot != bRoot) {
            parents[bRoot] = aRoot;
            return true;
        }
        return false;
    }

    private static int find(int a) {
        if (parents[a] < 0) return a;

        return parents[a] = find(parents[a]);
    }

    private static int stoi(String str) {
        return Integer.parseInt(str);
    }
}