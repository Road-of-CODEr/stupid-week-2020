
/**
 * 1608. Special Array With X Elements Greater Than or Equal X
 * https://leetcode.com/contest/weekly-contest-209/problems/special-array-with-x-elements-greater-than-or-equal-x/
 * @param {number[]} nums
 * @return {number}
 */
const specialArray = (nums) => {
  const n = nums.length;
  if (n < 1) {
    return -1;
  }
  for (let i = 1; i <= n; i++) {
    let cnt = 0;
    for (let j = 0; j < n; j++) {
      if (nums[j] >= i) {
        cnt++;
      }
    }
    if (i === cnt) return cnt;
  }
  return -1;
};