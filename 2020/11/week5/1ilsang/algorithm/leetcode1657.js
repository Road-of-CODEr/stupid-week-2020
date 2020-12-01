/**
 * @param {string} word1
 * @param {string} word2
 * @return {boolean}
 */
const closeStrings = (word1, word2) => {
  if (word1.length != word2.length) return false;
  const arr1 = Array(26).fill(0);
  const arr2 = Array(26).fill(0);

  for (let i = 0; i < word1.length; i++) {
    arr1[word1[i].charCodeAt() - 97]++;
    arr2[word2[i].charCodeAt() - 97]++;
  }

  for (let i = 0; i < 26; i++) {
    if ((arr1[i] && !arr2[i]) || (!arr1[i] && arr2[i])) return false;
  }
  arr1.sort((a, b) => b - a);
  arr2.sort((a, b) => b - a);

  for (let i = 0; i < 26; i++) {
    if (arr1[i] != arr2[i]) return false;
  }
  return true;
};
