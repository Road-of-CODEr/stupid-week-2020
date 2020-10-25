
/**
 * 1614. Maximum Nesting Depth of the Parentheses
 * https://leetcode.com/contest/weekly-contest-210/problems/maximum-nesting-depth-of-the-parentheses/
 * @param {string} s
 * @return {number}
 */
const maxDepth = (s) => {
  let stack = 0;
  let count = 0;

  for (const ltr of s) {
    if (ltr === '(') {
      stack++;
      if (count < stack) count = stack;
    } else if (ltr === ')') stack--;
  }
  
  return count;
}