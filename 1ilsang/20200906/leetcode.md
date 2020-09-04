[1560. Most Visited Sector in a Circular Track](https://leetcode.com/contest/weekly-contest-203/problems/most-visited-sector-in-a-circular-track/)

```javascript
/**
 * @param {number} n
 * @param {number[]} rounds
 * @return {number[]}
 */
const mostVisited = (n, rounds) => {
  const [start, end] = [rounds[0], rounds[rounds.length - 1]];
  const ret = [];

  if (start <= end) for (let i = start; i <= end; i++) ret.push(i);
  else {
    for (let i = 1; i <= end; i++) ret.push(i);
    for (let i = start; i <= n; i++) ret.push(i);
  }

  return ret;
};
```
