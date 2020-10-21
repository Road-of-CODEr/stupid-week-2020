class Solution {
public:
vector<int> ans;
int dist[16][16];
vector<int> countSubgraphsForEachDiameter(int n, vector<vector<int>>& edges) {
	ans.resize(n - 1);
	for (int i = 1; i <= n; i++)for (int j = 1; j <= n; j++) dist[i][j] = 99999;
	for (int i = 0; i < edges.size(); i++) {
		int u = edges[i][0], v = edges[i][1];
		dist[u][v] = 1;
		dist[v][u] = 1;
	}
	for (int i = 1; i <= n ; i++) dist[i][i] = 0;

	for (int k = 1; k <= n; k++) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if(dist[i][k]+dist[k][j]<dist[i][j])
					dist[i][j] = dist[i][k] + dist[k][j];
			}
		}
	}
	int set = (1 << n) - 1;
	for (int subset = set; subset; subset=(subset - 1) & set) {
		vector<int> v;
		for (int i = 1; i <= n; i++) {
			if (subset & (1<<(i-1))) v.push_back(i);
		}
		int len = 0;
		for (int i = 0; i < v.size(); i++) {
			for (int j = i + 1; j < v.size(); j++) {
				if (dist[v[i]][v[j]]) {
					len = max(len, dist[v[i]][v[j]]);
				}
			}
		}
		int edge = 0;
		for (int i = 0; i < v.size(); i++) {


			for (int j = i+1; j < v.size(); j++) {
				if (dist[v[i]][v[j]] == 1) {
					edge++;
				}
			}
		}
		
		if (len >= 1 && edge == v.size() - 1) {
			ans[len - 1]++;
		}
	}
	

	return ans;
}
};