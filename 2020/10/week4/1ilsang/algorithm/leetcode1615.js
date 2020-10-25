/**
 * 1615. Maximal Network Rank
 * https://leetcode.com/contest/weekly-contest-210/problems/maximal-network-rank/
 * @param {number} n
 * @param {number[][]} roads
 * @return {number}
 */
const maximalNetworkRank = (n, roads) => {
  const count = Array(n).fill(0);

  const mat = Array(n)
    .fill(null)
    .map(() => Array(n).fill(false));

  roads.forEach(([a, b]) => {
    mat[a][b] = true;
    mat[b][a] = true;

    count[a]++;
    count[b]++;
  });

  let res = 0;

  for (let i = 0; i < n; i++) {
    for (let j = i + 1; j < n; j++) {
      let aux = count[i] + count[j] - (mat[i][j] ? 1 : 0);
      res = Math.max(aux, res);
    }
  }

  return res;
};