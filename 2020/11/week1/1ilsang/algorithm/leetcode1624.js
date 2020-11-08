/**
 * @param {string} s
 * @return {number}
 */
const maxLengthBetweenEqualCharacters = (s) => {
  let res = -1;

  const map = {};
  for (let i = 0; i < s.length; i++) {
    if (map[s[i]] == undefined) map[s[i]] = i;
    else res = Math.max(res, i - map[s[i]]);
  }

  return res == -1 ? res : res - 1;
};
