class Solution {
public:
map<string, int> m;
string findLexSmallestString(string s, int a, int b) {
	
	string ans = s;
	queue<string> q;
	q.push(s);
	m[s] = 1;
	while (!q.empty()) {
		string ts = q.front();
		q.pop();
		ans = min(ans,ts);
		string ns = ts;
		for (int i = 1; i < s.size(); i += 2) {
			ns[i] = (((ns[i] - '0') + a) % 10) + '0';
		}
		if(m.find(ns)==m.end()){
			m[ns] = 1;
			q.push(ns);
		}
		ns = ts.substr(s.size() - b) + ts.substr(0, s.size() - b);
		if (m.find(ns) == m.end()){
			m[ns] = 1;
			q.push(ns);
		}
	}
	cout << ans;
	return ans;
}
};