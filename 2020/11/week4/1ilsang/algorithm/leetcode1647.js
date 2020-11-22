/**
 * https://leetcode.com/contest/weekly-contest-214/problems/minimum-deletions-to-make-character-frequencies-unique/
 * 1647. Minimum Deletions to Make Character Frequencies Unique
 * @param {string} s
 * @return {number}
 */
const minDeletions = (s) => {
  const arr = new Array(26).fill(0);
  let answer = 0;

  Array.from(s).forEach((e) => arr[e.charCodeAt() - 97]++);
  arr.sort((a, b) => b - a);

  for (let i = 1; i < 26; i++) {
    while (arr[i] && arr[i - 1] <= arr[i]) {
      arr[i]--;
      answer++;
    }
  }
  return answer;
};
