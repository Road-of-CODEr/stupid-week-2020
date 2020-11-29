class Solution {
public:
bool closeStrings(string word1, string word2) {
	int alpha[2][28];
	memset(alpha, 0, sizeof(alpha));
	if (word1.size() != word2.size()) return false;
	for (int i = 0; i < word1.size(); i++) {
		alpha[0][word1[i] - 'a']++;
		alpha[1][word2[i] - 'a']++;
	}
	vector<int> a, b;
	bool ret = true;
	for (int i = 0; i < 27; i++) {
		if ((alpha[0][i] == 0 && alpha[1][i] != 0) || (alpha[0][i] != 0 && alpha[1][i] == 0)) {
			ret = false;
			break;
		}
		a.push_back(alpha[0][i]);
		b.push_back(alpha[1][i]);
	}
	sort(a.begin(), a.end());
	sort(b.begin(), b.end());
	for (int i = 0; i < a.size(); i++) {
		if (a[i] != b[i]) {
			ret = false;
			break;
		}
	}

	return ret;
}
};