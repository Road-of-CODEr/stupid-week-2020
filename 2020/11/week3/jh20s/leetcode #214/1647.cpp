class Solution {
public:
int minDeletions(string s) {
	priority_queue<int> pq;
	char alpha[27];
	int ans = 0;
	memset(alpha, 0, sizeof(alpha));
	for (int i = 0; i < s.size(); i++) {
		int c = s[i] - 'a';
		alpha[c]++;
	}
	for (int i = 0; i < 27; i++) {
		pq.push(alpha[i]);
	}
	int now = 99999999;
	while (!pq.empty()) {
		int top = pq.top();
		pq.pop();
		if (top == 0) {
			continue;
		}
		if (top == now) {
			pq.push(top - 1);
			ans++;
		}
		else {
			now = top;
		}
	}
	return ans;
}
};