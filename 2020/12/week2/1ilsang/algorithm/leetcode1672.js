/**
 * https://leetcode.com/contest/weekly-contest-217/problems/richest-customer-wealth/
 * 1672. Richest Customer Wealth
 * @param {number[][]} accounts
 * @return {number}
 */
const maximumWealth = (accounts) => {
  const reduceArr = accounts.reduce((acc, cur) => {
    const sum = cur.reduce((acc, cur) => {
      acc += cur;
      return acc;
    }, 0);
    acc.push(sum);
    return acc;
  }, []);
  return Math.max(...reduceArr);
};
