[1480. Running Sum of 1d Array](https://leetcode.com/contest/weekly-contest-193/problems/running-sum-of-1d-array/)

```javascript
/**
 * @param {number[]} nums
 * @return {number[]}
 */
const runningSum = (nums) => {
  const answer = [];
  nums.reduce((acc, cur) => {
    acc += cur;
    answer.push(acc);
    return acc;
  }, 0);
  return answer;
};
```

[1481. Least Number of Unique Integers after K Removals](https://leetcode.com/contest/weekly-contest-193/problems/least-number-of-unique-integers-after-k-removals/)

```javascript
/**
 * @param {number[]} arr
 * @param {number} k
 * @return {number}
 */
const findLeastNumOfUniqueInts = (arr, k) => {
  const hit = {};
  let cnt = 0;
  arr.forEach((e) => {
    if (!hit[e]) {
      hit[e] = 0;
      cnt++;
    }
    hit[e]++;
  });
  const countes = Object.entries(hit).sort((a, b) => a[1] - b[1]);
  for (let i = 0; i < countes.length; i++) {
    if (k >= countes[i][1]) {
      k -= countes[i][1];
      cnt--;
    } else {
      break;
    }
  }
  return cnt;
};
```
