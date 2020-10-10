/**
 * 1598. Crawler Log Folder
 * https://leetcode.com/contest/weekly-contest-208/problems/crawler-log-folder/
 * @param {string[]} logs
 * @return {number}
 */
const minOperations = (logs) => {
  const answer = logs.reduce((acc, cur) => {
    if(cur === '../') {
      if(acc > 0) acc--;
    }
    else if(cur !== './') acc++;
    return acc;
  }, 0);
  return answer;
};