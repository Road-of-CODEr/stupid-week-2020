// [1568. Minimum Number of Days to Disconnect Island](https://leetcode.com/contest/weekly-contest-204/problems/minimum-number-of-days-to-disconnect-island/)
/**
 * @param {number[][]} grid
 * @return {number}
 */
const minDays = (grid) => {
  const landSum = grid.reduce((acc, cur) => {
    acc += cur.filter((e) => e > 0).length;
    return acc;
  }, 0);
  if (landSum == 1) return 1;

  // PHASE 1
  for (let i = 0; i < grid.length; i++) {
    let flag = false;
    for (let j = 0; j < grid[0].length; j++) {
      if (!grid[i][j]) continue;

      const visitedSum = BFS({
        grid,
        i,
        j
      });
      if (visitedSum != landSum) return 0;

      flag = true;
      break;
    }
    if (flag) break;
  }

  // PHASE 2
  for (let i = 0; i < grid.length; i++) {
    for (let j = 0; j < grid[0].length; j++) {
      if (!grid[i][j]) continue;

      grid[i][j] = 0;

      const visitedSum = BFS({
        grid,
        i,
        j
      });
      if (visitedSum != landSum - 1) return 1;

      grid[i][j] = 1;
    }
  }

  return 2;
};

const dr = [0, 1, 0, -1];
const dc = [1, 0, -1, 0];
const BFS = ({
  grid,
  i,
  j
}) => {
  const visit = grid.reduce((acc, cur) => {
    acc.push(new Array(cur.length).fill(false));
    return acc;
  }, []);
  const queue = [];
  let count = 0;

  for (let k = 0; k < 4; k++) {
    const nr = i + dr[k];
    const nc = j + dc[k];
    if (
      nr < 0 ||
      nc < 0 ||
      nr >= grid.length ||
      nc >= grid[0].length ||
      visit[nr][nc] ||
      !grid[nr][nc]
    )
      continue;
    visit[nr][nc] = true;
    count++;
    queue.push({
      r: nr,
      c: nc
    });
    break;
  }

  while (queue.length > 0) {
    const {
      r,
      c
    } = queue.shift();

    for (let i = 0; i < 4; i++) {
      const nr = r + dr[i];
      const nc = c + dc[i];
      if (
        nr < 0 ||
        nc < 0 ||
        nr >= grid.length ||
        nc >= grid[0].length ||
        visit[nr][nc] ||
        !grid[nr][nc]
      )
        continue;
      visit[nr][nc] = true;
      count++;
      queue.push({
        r: nr,
        c: nc
      });
    }
  }

  return count;
};