/**
 * https://leetcode.com/contest/weekly-contest-206/problems/count-unhappy-friends
 * @param {number} n
 * @param {number[][]} preferences
 * @param {number[][]} pairs
 * @return {number}
 */
const unhappyFriends = (n, preferences, pairs) => {
  let happyMap = new Array(n);
  for (let [i, j] of pairs) {
    happyMap[i] = preferences[i].indexOf(j);
    happyMap[j] = preferences[j].indexOf(i);
  }
    
  let unhappy = 0;
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < happyMap[i]; j++) {
      let partner = preferences[i][j];
      if (preferences[partner].indexOf(i) < happyMap[partner]) {
        unhappy++;
        break;
      }
    }
  }
    
  return unhappy;
};