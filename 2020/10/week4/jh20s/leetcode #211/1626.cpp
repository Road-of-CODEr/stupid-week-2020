class Solution {
public:
    vector<pair<int,int>> v;

int dp[1005];
int n;

int go(int num) {
	int& ret = dp[num];
	if (ret != -1) return ret;
	ret = v[num].second;
	for (int i = num + 1; i < n; i++) {
		if(v[num].first==v[i].first)	
			ret = max(ret, v[num].second + go(i));
		else if (v[num].first < v[i].first && v[num].second <= v[i].second)
			ret = max(ret, v[num].second + go(i));

	}
	return ret;
}

int bestTeamScore(vector<int>& scores, vector<int>& ages) {
	memset(dp, -1, sizeof(dp));
	n = scores.size();
	for (int i = 0; i < n; i++) {
		v.push_back({ ages[i], scores[i] });
	}
	sort(v.begin(), v.end());
	int ret = 0;
	for (int i = 0; i < n; i++) {
		ret = max(ret, go(i));
	}
	return ret;
}
};