[859. Buddy Strings](https://leetcode.com/problems/buddy-strings/)

```javascript
/**
 * @param {string} A
 * @param {string} B
 * @return {boolean}
 */
const buddyStrings = (A, B) => {
  if (A.length !== B.length) return false;
  else if (A === B) {
    const set = new Set(A);
    return set.size !== B.length;
  }

  let a = "",
    b = "";
  for (let i = 0; i < A.length; i++) {
    if (A[i] !== B[i]) {
      a += A[i];
      b += B[i];
    }
  }

  if (a.length === 2 && a.length === b.length) {
    return a[0] === b[1] && a[1] === b[0];
  }
  return false;
};
```

[136. Single Number](https://leetcode.com/problems/single-number/)

```javascript
/**
 * @param {number[]} nums
 * @return {number}
 */
const singleNumber = (nums) => {
  for (let i = 1; i < nums.length; i++) {
    nums[0] = nums[0] ^ nums[i];
  }
  return nums[0];
};
```

비트연산은 볼때마다 놀랍다 ㅡ.ㅡ;
