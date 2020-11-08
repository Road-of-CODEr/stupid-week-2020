class Solution {
public:

vector<int> parent;
int find(int u) {
	if (parent[u] < 0) {
		return u;
	}
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

vector<vector<int>> matrixRankTransform(vector<vector<int>>& matrix) {
	vector<vector<int>> ans;
	vector<pair<int, pair<int, int>>> v;
	map<int, int> m;
	int R = matrix.size(), C = matrix[0].size();
	ans.resize(R);
	
	for (int i = 0; i < R; i++){
		for (int j = 0; j < C; j++) {
			ans[i].push_back(0);
			v.push_back({ matrix[i][j],{i,j} });
			m[matrix[i][j]]++;
		}
	}
	sort(v.begin(), v.end());
	int cnt = 0;
	vector<pair<int, int>> prev;
	for (int i = 0; i < v.size(); i++) {
		int val = v[i].first;
		int r = v[i].second.first, c = v[i].second.second;
		int maxNum = 0;
		if (cnt == 0) cnt= m[val];


		for (int j = 0; j < R; j++) {
			if (matrix[j][c] == val) continue;
			maxNum = max(maxNum, ans[j][c]);
		}
		for (int j = 0; j < C; j++) {
			if (matrix[r][j] == val) continue;
			maxNum = max(maxNum, ans[r][j]);
		}
		ans[r][c] = maxNum +1;
		cnt--;
		prev.push_back({r,c});
		if (cnt == 0) {
			parent.clear();
			parent.resize(R+C,-1);
			map<int, int> maxU;
			for (int j = 0; j < prev.size(); j++) {
				int pr = prev[j].first, pc = prev[j].second;
				merge(pr, pc + R);
			}
			for (int j = 0; j < prev.size(); j++) {
				int pr = prev[j].first, pc = prev[j].second;
				int u = find(pr);
				maxU[u] = max(maxU[u], ans[pr][pc]);
			}
			for (int j = 0; j < prev.size(); j++) {
				int pr = prev[j].first, pc = prev[j].second;
				ans[pr][pc] = maxU[find(pr)];
			}
			prev.clear();
		}
	}
	return ans;
}
};