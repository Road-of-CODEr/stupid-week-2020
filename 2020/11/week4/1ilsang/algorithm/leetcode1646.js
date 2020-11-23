/**
 * https://leetcode.com/contest/weekly-contest-214/problems/get-maximum-in-generated-array/
 * 1646. Get Maximum in Generated Array
 * @param {number} n
 * @return {number}
 */
const getMaximumGenerated = (n) => {
  if (n < 2) return n;
  let answer = 1;
  const arr = [0, 1];
  for (let i = 2; i <= n; i++) {
    const quotient = Math.floor(i / 2);
    const value = i % 2 ? arr[quotient] + arr[quotient + 1] : arr[quotient];
    if (answer < value) answer = value;
    arr[i] = value;
  }
  return answer;
};
