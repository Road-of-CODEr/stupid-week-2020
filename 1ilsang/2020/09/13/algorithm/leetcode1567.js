// [1567. Maximum Length of Subarray With Positive Product](https://leetcode.com/contest/weekly-contest-204/problems/maximum-length-of-subarray-with-positive-product/)
/**
 * @param {number[]} nums
 * @return {number}
 */
const getMaxLen = (nums) => {
  let pos = 0;
  let neg = 0;
  let answer = 0;

  for (const cur of nums) {
    if (cur === 0) pos = neg = 0;
    else if (cur > 0) {
      pos = pos ? pos + 1 : 1;
      neg = neg ? neg + 1 : 0;
    } else {
      const [tmpP, tmpN] = [pos, neg];
      pos = tmpN ? tmpN + 1 : 0;
      neg = tmpP ? tmpP + 1 : 1;
    }

    answer = Math.max(answer, pos);
  }

  return answer;
};