[1534. Count Good Triplets](https://leetcode.com/contest/weekly-contest-200/problems/count-good-triplets/)

```javascript
/**
 * @param {number[]} arr
 * @param {number} a
 * @param {number} b
 * @param {number} c
 * @return {number}
 */
const countGoodTriplets = (arr, a, b, c) => {
    let answer = 0;
    for(let i = 0; i < arr.length - 2; i++) {
        for(let j = i + 1; j < arr.length - 1; j++) {
            if(Math.abs(arr[i] - arr[j]) > a) continue;
            for(let k = j + 1; k < arr.length; k++) {
                if(Math.abs(arr[j] - arr[k]) > b|| Math.abs(arr[i] - arr[k]) > c) continue;
                answer++;
            }
        }
    }
    return answer;
};
```

[1535. Find the Winner of an Array Game](https://leetcode.com/contest/weekly-contest-200/problems/find-the-winner-of-an-array-game/)

```javascript
/**
 * @param {number[]} arr
 * @param {number} k
 * @return {number}
 */
const getWinner = (arr, k) => {
    let winner = arr[0];
    let count = 0;
    for(let i = 1; i < arr.length; i++) {
        if(winner < arr[i]) {
            winner = arr[i];
            count = 1;
        } else count++;
        if(count === k) return winner;
    }
    return Math.max(...arr);
};
```
