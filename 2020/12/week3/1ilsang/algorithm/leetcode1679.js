/**
 * https://leetcode.com/contest/weekly-contest-218/problems/max-number-of-k-sum-pairs/
 * 1679. Max Number of K-Sum Pairs
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
const maxOperations = (nums, k) => {
  const m = new Map();
  let ans = 0;
  for (let n of nums) {
    if (n < k) {
      if (m.get(k - n)) m.set(k - n, m.get(k - n) - 1), ans++;
      else m.set(n, (m.get(n) || 0) + 1);
    }
  }
  return ans;
};
