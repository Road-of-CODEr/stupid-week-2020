class Solution {
public:
   int maxDepth(string s) {

	vector<int> v;
	int ret = 0;
	for (int i = 0; i < s.size(); i++) {
		if (s[i] == '(') {
			v.push_back(s[i]);
		} 
		else if (s[i] == ')') {
			v.pop_back();
		}
		ret = max(ret, (int)v.size());
	}
	return ret;
}

};