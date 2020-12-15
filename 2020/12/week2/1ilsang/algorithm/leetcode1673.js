/**
 * https://leetcode.com/contest/weekly-contest-217/problems/find-the-most-competitive-subsequence/
 * 1673. Find the Most Competitive Subsequence
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
const mostCompetitive = (nums, k) => {
  const stack = [];
  const len = nums.length;
  let toRemove = len - k;

  for (let i = 0; i < len; i++) {
    console.log(i, nums[i], toRemove, stack);
    while (stack.length && stack[stack.length - 1] > nums[i] && toRemove > 0) {
      stack.pop();
      toRemove--;
    }
    stack.push(nums[i]);
  }

  return stack.slice(0, k);
};
