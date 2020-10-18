import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int idx, dist;

        Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    static int N, E, dist[], v1, v2;
    static ArrayList<Node>[] list;
    static PriorityQueue<Node> pq = new PriorityQueue<Node>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<Node>();
        }

        dist = new int[N];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, cost));
            list[b].add(new Node(a, cost));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken()) - 1;
        v2 = Integer.parseInt(st.nextToken()) - 1;

        int route1 = 0, route2 = 0;
        boolean notExistRoute1 = false, notExistRoute2 = false;

        init(0);
        dijkstra();
        if (dist[v1] == Integer.MAX_VALUE)
            notExistRoute1 = true;
        else
            route1 += dist[v1];

        init(v2);
        dijkstra();
        if (dist[N - 1] == Integer.MAX_VALUE)
            notExistRoute1 = true;
        else
            route1 += dist[N - 1];

        init(0);
        dijkstra();
        if (dist[v2] == Integer.MAX_VALUE)
            notExistRoute2 = true;
        else
            route2 += dist[v2];

        init(v1);
        dijkstra();
        if (dist[N - 1] == Integer.MAX_VALUE)
            notExistRoute2 = true;
        else
            route2 += dist[N - 1];

        int rslt = 0;
        if (notExistRoute1 && notExistRoute2) {
            System.out.println("-1");
            return;
        } else if (notExistRoute1) {
            rslt = route2;
        } else if (notExistRoute2) {
            rslt = route1;
        } else {
            rslt = Math.min(route1, route2);
        }

        init(v1);
        dijkstra();
        if (dist[v2] == Integer.MAX_VALUE) {
            System.out.println("-1");
            return;
        } else
            rslt += dist[v2];

        System.out.println(rslt);
    }

    private static void dijkstra() {
        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            int now = temp.idx;
            int nowDist = temp.dist;

            if (nowDist > dist[now])
                continue;

            for (int i = 0; i < list[now].size(); i++) {
                Node nextTemp = list[now].get(i);
                int next = nextTemp.idx;
                int nextDist = nowDist + nextTemp.dist;

                if (nextDist >= dist[next])
                    continue;

                dist[next] = nextDist;
                pq.add(new Node(next, nextDist));
            }
        }
    }

    private static void init(int start) {
        pq.clear();
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Node(start, 0));
        dist[start] = 0;
    }
}