/**
 * https://leetcode.com/contest/weekly-contest-216/problems/smallest-string-with-a-given-numeric-value/
 * 1663. Smallest String With A Given Numeric Value
 * @param {number} n
 * @param {number} k
 * @return {string}
 */

const getSmallestString = (n, k) => {
  const result = [];
  const offset = "a".charCodeAt() - 1;

  for (let i = n - 1; i >= 0; i--) {
    const num = Math.min(k - i, 26);
    k -= num;
    result.unshift(String.fromCharCode(num + offset));
  }
  return result.join("");
};
