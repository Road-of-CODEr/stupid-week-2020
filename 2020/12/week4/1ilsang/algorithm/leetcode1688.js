/**
 * https://leetcode.com/contest/weekly-contest-219/problems/count-of-matches-in-tournament/
 * 1688. Count of Matches in Tournament
 * @param {number} n
 * @return {number}
 */
const numberOfMatches = (n) => {
  let current = n;
  let answer = 0;

  while (current > 1) {
    if (current % 2 === 0) {
      current /= 2;
      answer += current;
    } else {
      answer += (current - 1) / 2;
      current = (current - 1) / 2 + 1;
    }
  }

  return answer;
};
