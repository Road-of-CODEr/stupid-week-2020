/**
 * 1629. Slowest Key
 * https://leetcode.com/contest/weekly-contest-212/problems/slowest-key/
 * @param {number[]} releaseTimes
 * @param {string} keysPressed
 * @return {character}
 */
const slowestKey = (releaseTimes, keysPressed) => {
  const initKey = keysPressed[0].charCodeAt() - 97;
  const answer = {
    key: initKey,
    value: releaseTimes[0],
  };

  for (let i = 1; i < releaseTimes.length; i++) {
    const gap = releaseTimes[i] - releaseTimes[i - 1];
    const curKey = keysPressed[i].charCodeAt() - 97;

    if (answer.value < gap) {
      answer.key = curKey;
      answer.value = gap;
    } else if (answer.value === gap && answer.key < curKey) {
      answer.key = curKey;
    }
  }

  return String.fromCharCode(answer.key + 97);
};
