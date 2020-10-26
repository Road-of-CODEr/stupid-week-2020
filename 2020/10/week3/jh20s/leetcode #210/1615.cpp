class Solution {
public:
int check[105][105];
int maximalNetworkRank(int n, vector<vector<int>>& roads) {
	vector<vector<int>>v;
	v.resize(n + 1);
	int ret = 0;
	memset(check, 0, sizeof(check));
	for (int i = 0; i < roads.size(); i++) {
		int u = roads[i][0], w = roads[i][1];
		v[u].push_back(w);
		v[w].push_back(u);
		check[u][w] = 1;
		check[w][u] = 1;
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (i == j)continue;
			ret = max(ret, (int)(v[i].size() + v[j].size() - check[i][j]));
		}
	}
	return ret;
}
};