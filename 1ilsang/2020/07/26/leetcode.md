[1512. Number of Good Pairs](https://leetcode.com/contest/weekly-contest-197/problems/number-of-good-pairs/)

```javascript
/**
 * @param {number[]} nums
 * @return {number}
 */
const numIdenticalPairs = (nums) => {
  const hit = [];
  nums.forEach((e) => (hit[e] = isNaN(hit[e]) ? 1 : hit[e] + 1));
  const answer = hit.reduce((acc, cur) => {
    const n = cur - 1;
    acc += 0.5 * n * (n + 1);
    return acc;
  }, 0);
  return answer;
};
```

[1513. Number of Substrings With Only 1s](https://leetcode.com/contest/weekly-contest-197/problems/number-of-substrings-with-only-1s/)

```javascript
/**
 * @param {string} s
 * @return {number}
 */
const numSub = (s) => {
  const ones = s.split("0").filter((e) => e.length > 0);
  const mod = 10 ** 9 + 7;
  const answer = ones.reduce((acc, cur) => {
    const n = cur.length;
    acc += n * (n + 1) * 0.5;
    return acc;
  }, 0);
  return answer % mod;
};
```
