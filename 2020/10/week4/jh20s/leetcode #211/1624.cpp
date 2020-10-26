class Solution {
public:
    
int maxLengthBetweenEqualCharacters(string s) {
	int L[27], R[27];
	int ret = -1;
	for (int i = 0; i < 27; i++) {
		L[i] = 99999;
		R[i] = -1;
	}

	for (int i = 0; i < s.size(); i++) {
		int c = s[i] - 'a';
		L[c] = min(L[c], i);
		R[c] = max(R[c], i);
	}

	for (int i = 0; i < 27; i++) {
		ret = max(ret, R[i] - L[i]-1);
	}
	return ret;
}
};