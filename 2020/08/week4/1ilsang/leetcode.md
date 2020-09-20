[1544. Make The String Great](https://leetcode.com/contest/weekly-contest-201/problems/make-the-string-great/)

```javascript
/**
 * @param {string} s
 * @return {string}
 */
const makeGood = (s) => {
    const stack = [];
    
    [...s].forEach(e => {
        const TOP = stack.length - 1;
        if(TOP === -1) {
            stack.push(e);
            return;
        }
        
        Math.abs(stack[TOP].charCodeAt() - e.charCodeAt()) === 32
            ? stack.pop()
            : stack.push(e)
        ;
    });
    
    return stack.join('');
};
```

[1545. Find Kth Bit in Nth Binary String](https://leetcode.com/contest/weekly-contest-201/problems/find-kth-bit-in-nth-binary-string/)

```javascript
/**
 * @param {number} n
 * @param {number} k
 * @return {character}
 */
const findKthBit = (n, k) => {
    const s = [0];
    while(n-- > 0) {
        const reverseArr = [...s].reverse().map(e => e === 0 ? 1 : 0);
        s.push(1, ...reverseArr);
    }
    return `${s[k - 1]}`;
};
```

[1546. Maximum Number of Non-Overlapping Subarrays With Sum Equals Target](https://leetcode.com/contest/weekly-contest-201/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/)

```javascript
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
const maxNonOverlapping = (nums, target) => {
  const set = new Set([0]);
  const values = {
    acc: 0,
    count: 0
  };
  nums.forEach(e => {
    values.acc += e;
    if(set.has(values.acc - target)) {
      set.clear();
      set.add(0);
      values.acc = 0;
      values.count++;
    } else {
      set.add(values.acc);
    }
  });
  return values.count;
};
```

[1547. Minimum Cost to Cut a Stick](https://leetcode.com/contest/weekly-contest-201/problems/minimum-cost-to-cut-a-stick/)

```javascript
/**
 * @param {number} n
 * @param {number[]} cuts
 * @return {number}
 */
const minCost = (n, cuts) => {
  cuts.push(0, n);
  cuts.sort((a, b) => a - b);
  
  const memo = {};
  const go = (left, right) => {
    if(left + 1 === right) return 0;
    
    const idx = `${left},${right}`;
    if(memo[idx]) return memo[idx];
    
    const len = cuts[right] - cuts[left];
    let res = Infinity;
    
    for(let i = left + 1; i < right; i++) {
      res = Math.min(res, go(left, i) + go(i, right) + len);
    }
    return memo[idx] = res;
  }
  
  return go(0, cuts.length - 1);
};
```