/**
 * https://leetcode.com/contest/weekly-contest-216/problems/check-if-two-string-arrays-are-equivalent/
 * 1662. Check If Two String Arrays are Equivalent
 * @param {string[]} word1
 * @param {string[]} word2
 * @return {boolean}
 */
const arrayStringsAreEqual = (word1, word2) => {
  const prev = word1.join("");
  const next = word2.join("");

  return prev === next;
};
