/**
 * https://leetcode.com/contest/weekly-contest-218/problems/goal-parser-interpretation/
 * 1678. Goal Parser Interpretation
 * @param {string} command
 * @return {string}
 */
const interpret = (command) => {
  const stack = [];
  const answer = [];

  for (let i = 0; i < command.length; i++) {
    const curCmd = command[i];
    if (curCmd === "G") {
      answer.push(curCmd);
      continue;
    } else if (curCmd !== ")") {
      stack.push(curCmd);
      continue;
    }

    const tmpStack = [];
    while (stack.length > 0) {
      const prevCmd = stack.pop();
      if (prevCmd === "(") {
        const value = tmpStack.length === 0 ? "o" : "al";
        answer.push(value);
      } else {
        tmpStack.unshift(prevCmd);
      }
    }
  }

  return answer.join("");
};
