class UnionFind {
    vector<int> id;
public:
    UnionFind(int n) : id(n) {
        iota(begin(id), end(id), 0);
    }
    void connect(int a, int b) {
        int x = find(a), y = find(b);
        if (x == y) return;
        id[x] = y;
    }
    bool connected(int i, int j) { return find(i) == find(j); }
    int find(int a) {
        return id[a] == a ? a : (id[a] = find(id[a]));
    }
};
class Solution {
public:
    vector<bool> distanceLimitedPathsExist(int n, vector<vector<int>>& E, vector<vector<int>>& Q) {
        vector<bool> ans(Q.size());
        for (int i = 0; i < Q.size(); ++i) Q[i].push_back(i);
        sort(begin(Q), end(Q), [&](auto &a, auto &b) { return a[2] < b[2]; });
        sort(begin(E), end(E), [&](auto &a, auto &b) { return a[2] < b[2]; });
        UnionFind uf(n);
        int j = 0;
        for (auto &q : Q) {
            int u = q[0], v = q[1], limit = q[2];
            for (; j < E.size(); ++j){ 
                auto &e = E[j];
                int x = e[0], y = e[1], z = e[2];
                if (z >= limit) break;
                uf.connect(x, y);
            }
            ans[q[3]] = uf.connected(u, v);
        }
        return ans;
    }
};