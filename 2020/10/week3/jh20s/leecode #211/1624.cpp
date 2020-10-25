class Solution {
public:
int parent[100005];
int find(int u) {
	if (parent[u] < 0) return u;
	return parent[u] = find(parent[u]);
}

int merge(int u, int v) {
	u = find(u);
	v = find(v);
	if (u == v) return parent[u];
	parent[u] += parent[v];
	parent[v] = u;
	return parent[u];
}

vector<bool> areConnected(int n, int threshold, vector<vector<int>>& queries) {
	memset(parent, -1, sizeof(parent));
	vector<bool> ans;
	for (int i = threshold+1; i <= n/2; i++) {

		for (int j = i; j<= n; j++) {
			if (j % i == 0) {
				merge(i, j);
			}
		}
	}

	for (int i = 0; i < queries.size(); i++) {
		int a = find(queries[i][0]), b = find(queries[i][1]);
		if (a == b) ans.push_back(true);
		else ans.push_back(false);
	}
	return ans;
}

};