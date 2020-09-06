
[1550. Three Consecutive Odds](https://leetcode.com/contest/weekly-contest-202/problems/three-consecutive-odds/)

```javascript
/**
 * @param {number[]} arr
 * @return {boolean}
 */
const threeConsecutiveOdds = (arr) => {
  let count = 0;
  for(let i = 0; i < arr.length; i++) {
    if(arr[i] % 2 === 0) {
      count = 0;
      continue;
    } else if(++count === 3) {
      return true;
    }
  }
  return false;
};
```

[1551. Minimum Operations to Make Array Equal](https://leetcode.com/contest/weekly-contest-202/problems/minimum-operations-to-make-array-equal/)

```javascript
/**
 * @param {number} n
 * @return {number}
 */
 // O(N) solution
const minOperations = (n) => {
  let answer = 0;
  for(let i = 0; i < n / 2; i++) {
    const x = (2 * i) + 1;
    const y = (2 * (n - i - 1)) + 1;
    answer += (y - x) / 2;
  }
  return answer;
};

// O(1) solution
const minOperations = (n) => {
  const m = Math.floor(n / 2);
  return n % 2 === 0 ? m * m : m * (m + 1);
};
```

[1552. Magnetic Force Between Two Balls](https://leetcode.com/contest/weekly-contest-202/problems/magnetic-force-between-two-balls/)

```javascript
/**
 * @param {number[]} position
 * @param {number} m
 * @return {number}
 */
const maxDistance = (position, m) => {
  position.sort((a, b) => a - b);
  
  let l = 0;
  let r = position[position.length - 1] - position[0];
  let answer = 0;
  
  while(l <= r) {
    let mid = (l + r) >> 1;
    if(isPossible(position, m, mid)) {
      answer = Math.max(answer, mid);
      l = mid + 1;
    } else r = mid - 1;
  }
  
  return answer;
}

const isPossible = (position, m, gap) => {
  let prev = 0;
  for(let i = 0; i < position.length; i++) {
    if(prev !== 0 && position[i] - prev < gap) continue;
    prev = position[i];
    m--;
  }
  return m <= 0;
}
```

[1553. Minimum Number of Days to Eat N Oranges](https://leetcode.com/contest/weekly-contest-202/problems/minimum-number-of-days-to-eat-n-oranges/)

```javascript
/**
 * @param {number} n
 * @return {number}
 */
const minDays = (n) => go(n, 1);

const memo = {};

const go = (cur, depth) => {
  if(memo[cur]) return memo[cur];
  else if(cur === 0) return 0;
  else if(depth > 99) return Infinity;

  depth++;
  let ret = Infinity;
  if(cur % 3 === 0) ret = Math.min(ret, go(cur / 3, depth) + 1);
  if(cur % 2 === 0) ret = Math.min(ret, go(cur / 2, depth) + 1);
  ret = Math.min(ret, go(cur - 1, depth) + 1);
  depth--;
  
  if(ret != Infinity) memo[cur] = ret;
  return ret;
};
```
