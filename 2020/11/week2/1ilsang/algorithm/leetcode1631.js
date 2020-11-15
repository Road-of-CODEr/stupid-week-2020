/**
 * 1631. Path With Minimum Effort
 * https://leetcode.com/contest/weekly-contest-212/problems/path-with-minimum-effort/
 * @param {number[][]} heights
 * @return {number}
 */
const minimumEffortPath = (heights) => {
  const n = heights.length;
  const m = heights[0].length;
  const moves = [
    [0, 1],
    [0, -1],
    [1, 0],
    [-1, 0],
  ];
  let lo = 0;
  let hi = 1e6;
  let result = Infinity;

  //bfs
  const isPossible = (effort, seen) => {
    let q = [[0, 0]];
    seen.add([0, 0].toString());
    while (q.length) {
      const temp = [];
      while (q.length) {
        const [i, j] = q.shift();
        if (i == n - 1 && j == m - 1) return true;
        for (const [dx, dy] of moves)
          if (
            i + dx >= 0 &&
            i + dx < n &&
            j + dy >= 0 &&
            j + dy < m && //inbound neighbor
            !seen.has([i + dx, j + dy].toString()) && // not  explored
            Math.abs(heights[i + dx][j + dy] - heights[i][j]) <= effort //with good diff
          )
            seen.add([i + dx, j + dy].toString()), //explore it
              temp.push([i + dx, j + dy]);
      }
      q = temp;
    }
    return false;
  };

  while (lo <= hi) {
    let mid = (lo + hi) >> 1;
    let seen = new Set();
    if (isPossible(mid, seen)) {
      result = Math.min(result, mid);
      hi = mid - 1;
    } else lo = mid + 1;
  }

  return result;
};
