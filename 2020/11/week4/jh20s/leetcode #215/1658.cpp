class Solution {
public:

int MAX = 1000000000;
int minOperations(vector<int>& nums, int x) {
	int sum = 0, l = 0;
	int cnt = 0, ret = MAX;
	while (l < nums.size()) {
		if (sum + nums[l] <= x) {
			sum += nums[l++];
			if (sum == x) {
				ret = l;
				break;
			}
		}
		else
			break;
	}
	
	for(int r = nums.size()-1,rcnt=0;r>=0;){
		if (l<r && sum + nums[r] <= x) {
				sum += nums[r--];
				rcnt++;
				if (sum == x) {
					ret = min(ret, l + rcnt);
				}
			}
		else {
			if (l == 0)
				break;
			sum -= nums[--l];
		}
	}
	return ret==MAX?-1:ret;
}
};