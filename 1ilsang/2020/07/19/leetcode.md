[1502. Can Make Arithmetic Progression From Sequence](https://leetcode.com/contest/weekly-contest-196/problems/can-make-arithmetic-progression-from-sequence/)

```javascript
/**
 * @param {number[]} arr
 * @return {boolean}
 */
const canMakeArithmeticProgression = (arr) => {
  arr.sort((a, b) => Number(a) - Number(b));
  let gap = arr[0] - arr[1];
  for (let i = 2; i < arr.length; i++) {
    if (gap !== arr[i - 1] - arr[i]) return false;
  }
  return true;
};
```

[1503. Last Moment Before All Ants Fall Out of a Plank](https://leetcode.com/contest/weekly-contest-196/problems/last-moment-before-all-ants-fall-out-of-a-plank/)

```javascript
/**
 * @param {number} n
 * @param {number[]} left
 * @param {number[]} right
 * @return {number}
 */
const getLastMoment = (n, left, right) => {
  return Math.max(Math.max(...left), n - Math.min(...right));
};
```
