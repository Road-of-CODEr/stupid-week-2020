[1518. Water Bottles](https://leetcode.com/contest/weekly-contest-198/problems/water-bottles/)

```javascript
/**
 * @param {number} numBottles
 * @param {number} numExchange
 * @return {number}
 */
const numWaterBottles = (numBottles, numExchange) => {
  let answer = numBottles;
  while (numBottles >= numExchange) {
    const drinks = Math.floor(numBottles / numExchange);
    const remain = numBottles % numExchange;
    answer += drinks;
    numBottles = drinks + remain;
  }
  return answer;
};
```

[549. Detect Capital](https://leetcode.com/explore/challenge/card/august-leetcoding-challenge/549/week-1-august-1st-august-7th/3409/)

```javascript
/**
 * @param {string} word
 * @return {boolean}
 */
const detectCapitalUse = (word) => {
  if (word.toLowerCase() === word || word.toUpperCase() === word) return true;
  const rest = word.slice(1);
  if (word[0].toUpperCase() === word[0] && rest.toLowerCase() === rest)
    return true;
  return false;
};
```
