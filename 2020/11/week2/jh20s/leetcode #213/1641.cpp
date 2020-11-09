class Solution {
public:
int furthestBuilding(vector<int>& heights, int bricks, int ladders) {
	
	priority_queue<int> pq;
	int ans = 0;
	for (int i = 1; i < heights.size(); i++) {
		int diff = heights[i] - heights[i - 1];
		int flag = 0;
		if (diff > 0) {
			if (bricks - diff >= 0) {
				bricks -= diff;
				pq.push(diff);
				ans = i;
			}
			else {
				if (ladders && (pq.empty() || pq.top() < diff)) {
					ladders--;
					ans = i;
				}
				else{
					while (!pq.empty() && ladders) {
						ladders--;
						bricks += pq.top(); 
						pq.pop();
						if (bricks - diff >= 0) {
							bricks -= diff;
							pq.push(diff);
							ans = i;
							flag = 1;
							break;
						}
					}
					if (flag == 0) {
						break;
					}
				}
			}
		}
		else {
			ans = i;
		}
	}
	return ans;
}
};