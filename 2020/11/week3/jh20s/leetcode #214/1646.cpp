class Solution {
public:
bool canFormArray(vector<int>& arr, vector<vector<int>>& pieces) {
	map<int, int> m;
	vector<int> v;
	int check[105];
	int here[105];
	memset(check, 0, sizeof(check));
	memset(here, 0, sizeof(here));
	for (int i = 0; i < arr.size(); i++) {
		here[arr[i]] = i;
	}

	for (int i = 0; i < pieces.size(); i++) {
		for (int j = here[pieces[i][0]],k=0; k < pieces[i].size(); j++, k++) {
			if (arr[j] != pieces[i][k] || check[pieces[i][k]])
				return false;
			else {
				check[pieces[i][k]] = 1;
			}
		}
	}
	return true;

}
};