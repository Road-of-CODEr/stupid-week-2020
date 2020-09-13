1441. Build an array with stack operations.

---

- [Go to problem](https://leetcode.com/contest/weekly-contest-188/problems/build-an-array-with-stack-operations/)

```javascript
/**
 * @param {number[]} target
 * @param {number} n
 * @return {string[]}
 */
const STR_PUSH = "Push";
const STR_POP = "Pop";

const buildArray = (target, n) => {
  let num = 1;

  const answer = target.reduce((acc, cur) => {
    while (cur !== num) {
      acc.push(...[STR_PUSH, STR_POP]);
      num++;
    }
    acc.push(STR_PUSH);
    num++;
    return acc;
  }, []);
  return answer;
};
```

1431. Kids With the Greatest Number of Candies.

---

```javascript
/**
 * @param {number[]} candies
 * @param {number} extraCandies
 * @return {boolean[]}
 */
const kidsWithCandies = (candies, extraCandies) => {
  const maxValue = candies.reduce((acc, cur) => {
    if (acc < cur) acc = cur;
    return acc;
  }, 0);
  const ret = candies.reduce((acc, cur) => {
    const flag = cur + extraCandies >= maxValue;
    acc.push(flag);
    return acc;
  }, []);
  return ret;
};
```

1460. Make Two Arrays Equal by Reversing Sub-arrays

---

- [URL](https://leetcode.com/contest/biweekly-contest-27/problems/make-two-arrays-equal-by-reversing-sub-arrays/)

```javascript
/**
 * @param {number[]} target
 * @param {number[]} arr
 * @return {boolean}
 */
const canBeEqual = (target, arr) => {
  const hit = [];
  target.forEach((e) => {
    if (hit[e] > 0) {
      hit[e]++;
      return;
    }
    hit[e] = 1;
  });
  arr.forEach((e) => hit[e]--);
  for (let i = 0; i < hit.length; i++) {
    if (hit[i]) return false;
  }
  return true;
};
```
