class Solution {
public:
    int maxProfit(vector<int>& inventory, int orders) {
        int maxProfit(vector<int>& inv, int orders) {
    long res = 0, colors = 1;
    sort(begin(inv), end(inv));
    for (int i = inv.size() - 1; i >= 0 && orders > 0; --i, ++colors) {
        long cur = inv[i], prev = i > 0 ? inv[i - 1] : 0;
        long rounds = min(orders / colors, cur - prev);
        orders -= rounds * colors;
        res = (res + (cur * (cur + 1) - (cur - rounds) * (cur - rounds + 1)) / 2 * colors) % 1000000007;
        if (cur - prev > rounds) {
            res = (res + orders * (cur - rounds)) % 1000000007;
            break;
        }
    }
    return res;
}
    }
};