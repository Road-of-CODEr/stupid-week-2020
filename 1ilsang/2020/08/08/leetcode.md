[1528. Shuffle String](https://leetcode.com/contest/weekly-contest-199/problems/shuffle-string/)

```javascript
/**
 * @param {string} s
 * @param {number[]} indices
 * @return {string}
 */
const restoreString = (s, indices) => {
    const hitter = [];
    indices.forEach((value, index) => {
        hitter.push({
            index: value,
            char: s[index]
        })
    });
    hitter.sort((a, b) => a.index - b.index);
    const answer = hitter.reduce((acc, cur) => {
        acc += cur.char;
        return acc;
    }, '');
    return answer;
};
```

[1529. Bulb Switcher IV](https://leetcode.com/contest/weekly-contest-199/problems/bulb-switcher-iv/)

```javascript
/**
 * @param {string} target
 * @return {number}
 */
const minFlips = (target) => {
    let prev = '0';
    let answer = 0;
    for(let i = 0; i < target.length; i++) {
        if(target[i] === prev) continue;
        prev = target[i];
        answer++;
    }
    return answer;
};
```
