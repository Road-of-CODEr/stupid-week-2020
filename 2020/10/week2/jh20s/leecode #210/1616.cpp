class Solution {
public:
bool check2(string& a, int i, int j) {
	while (i < j && a[i] == a[j]) {
		i++,j--;
	}
	return j <= i;
}

bool check(string& a, string &b) {
	int i = 0, j = a.size()-1;
	while (i < j && a[i] == b[j]) {
		i++, j--;
	}
	return check2(a, i, j) || check2(b, i, j);
}

bool checkPalindromeFormation(string a, string b) {
	return check(a, b) || check(b, a);
}
};