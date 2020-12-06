class Solution {
public:

int minMoves(vector<int>& nums, int limit) {

	vector<int> v1(limit * 2 + 2, 0), v2(limit * 2 + 2, 0);

	int n = nums.size();

	for (int i = 0; 2 * i < n - 1; ++i) {
		v2[nums[i] + nums[n - i - 1]]++;
		v1[min(nums[i], nums[n - i - 1]) + 1]++;
		v1[max(nums[i], nums[n - i - 1]) + limit + 1]--;
	}

	int res = n;
	int curr = 0;

	for (int i = 2; i <= limit * 2; ++i) {
		curr += v1[i];
		int val = (n / 2 - curr) * 2 + curr - v2[i];
		res = min(res, val);
	}

	return res;
}
};