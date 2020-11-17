class Solution {
public:
int dr[4] = { 1, -1, 0, 0 };
int dc[4] = { 0,0,1,-1 };
int check[105][105];
bool go(int r, int c, vector<vector<int>>& heights, int mid) {
	if (check[r][c]) return false;
	if (r + 1 == heights.size() && c + 1 == heights[0].size())
		return true;
	check[r][c] = 1;
	for (int i = 0; i < 4; i++) {
		int nr = r + dr[i], nc = c + dc[i];
		if (0 <= nr && nr < heights.size() && 0 <= nc && nc < heights[0].size() 
			&& abs(heights[r][c] - heights[nr][nc]) <= mid && go(nr,nc,heights,mid)) {
			return true;
		}
	}
	return false;
}

int minimumEffortPath(vector<vector<int>>& heights) {
	int ans = 999999999;
	int l = 0, r = 1000000;
	int bit = 0;
	while (l <= r) {
		int mid = (l + r) / 2;
		memset(check, 0, sizeof(check));
		if (go(0, 0, heights, mid)) {
			r = mid - 1;
			ans = min(ans, mid);
		}
		else {
			l = mid + 1;
		}
		bit++;
	}
	return ans;
}
};