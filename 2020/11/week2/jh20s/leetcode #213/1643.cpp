class Solution {
public:
    size_t NChooseK(int n, int k) {
        return round(tgamma(n+1)/tgamma(k+1)/tgamma(n-k+1));
    }
    
    string kthSmallestPath(vector<int>& destination, int k) {
        int numHLeft = destination[1], numCharsToBuild = destination[0] + destination[1];
        uint64_t totalMax = NChooseK(numCharsToBuild, numHLeft);
        string ans = "";
        while (numHLeft && numHLeft < numCharsToBuild) {
            uint64_t maxH = totalMax*numHLeft/numCharsToBuild;
            if (k <= maxH) {
                ans += "H";
                numHLeft--;
                totalMax = maxH;
            } else {
                ans += "V";
                totalMax -= maxH;
				k -= maxH;
            }
            numCharsToBuild--;
        }
        ans += string(numCharsToBuild, numHLeft ? 'H' : 'V');
        return ans;
    }
};