class Solution {
public:
int L[2][1000005];//0 홀 1 짝
int R[2][1000005];
int waysToMakeFair(vector<int>& nums) {
	L[0][0] = nums[0];
	for (int i = 1; i < nums.size(); i++) {
		if (i % 2)L[1][i] += nums[i];
		else L[0][i] += nums[i];
		L[0][i] += L[0][i-1];
		L[1][i] += L[1][i-1];
	}
	for (int i = nums.size()-1; i >= 0; i--) {
		if (i % 2) R[0][i] += nums[i];
		else R[1][i] += nums[i];
		R[0][i] += R[0][i+1];
		R[1][i] += R[1][i+1];
	}
	int ans = 0;
	for (int i = 0; i < nums.size(); i++) {
		int odd = 0, even = 0;
		if(i!=0){
			odd += L[0][i-1];
			even += L[1][i-1];
		}
		if (i % 2) {
			odd += R[0][i + 1];
			even += R[1][i + 1];
		}
		else {
			odd += R[0][i + 1];
			even += R[1][i + 1];
		}
		if (odd == even)
			ans++;
	}
	return ans;
}
};