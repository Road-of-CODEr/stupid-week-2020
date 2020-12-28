class Solution {
public:
int maximumUniqueSubarray(vector<int>& nums) {
	
	int Sum = 0;
	int ans = 0;
	map<int, int> m;
	int l = 0;
	for (int i = 0; i < nums.size(); i++) {
		if (m.find(nums[i]) != m.end()) {
			int k = m[nums[i]];
			for (int j = l; j <= k; j++) {
				Sum -= nums[j];
				m.erase(nums[j]);
			}
			l = k+1;
		}
		m[nums[i]] = i;
		Sum += nums[i];
		ans = max(ans, Sum);
	}
	return ans;
}
};