/**
 * @param {number[]} scores
 * @param {number[]} ages
 * @return {number}
 */
const bestTeamScore = (scores, ages) => {
  const arr = [];
  const dp = [];
  let res = 0;
  for (let i = 0; i < ages.length; i++) {
    arr.push({
      s: scores[i],
      a: ages[i],
    });
  }

  arr.sort((a, b) => {
    if (a.a === b.a) {
      return b.s - a.s;
    }
    return b.a - a.a;
  });

  for (let i = 0; i < arr.length; i++) {
    let max = 0;
    for (let j = 0; j < i; j++) {
      // no conflicts
      if (arr[i].s <= arr[j].s) {
        max = Math.max(max, dp[j]);
      }
    }
    dp[i] = arr[i].s + max;
    res = Math.max(res, dp[i]);
  }
  return res;
};
