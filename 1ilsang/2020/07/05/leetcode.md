[1486. XOR Operation in an Array](https://leetcode.com/contest/weekly-contest-194/problems/xor-operation-in-an-array/)

```javascript
/**
 * @param {number} n
 * @param {number} start
 * @return {number}
 */
const xorOperation = (n, start) => {
  if (n === 1) return start;

  let answer = start ^ (start + 2);
  start += 4;

  for (let i = 2; i < n; i++) {
    answer ^= start;
    start += 2;
  }

  return answer;
};
```

[1487. Making File Names Unique](https://leetcode.com/contest/weekly-contest-194/problems/making-file-names-unique/)

```javascript
/**
 * @param {string[]} names
 * @return {string[]}
 */
const getFolderNames = (names) => {
  const answer = names.reduce((acc, cur) => {
    if (!acc.has(cur)) {
      acc.set(cur, 1);
      return acc;
    }

    let cnt = acc.get(cur);
    let next = `${cur}(${cnt})`;

    while (acc.has(next)) {
      cnt++;
      next = `${cur}(${cnt})`;
    }

    acc.set(cur, cnt);
    acc.set(next, 1);
    return acc;
  }, new Map());

  return [...answer.keys()];
};
```
