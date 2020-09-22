/**
 * https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
 * @param {string} s
 * @return {string}
 */
const modifyString = function (s) {
  const chars = s.split('');
  for (let i = 0; i < chars.length; ++i) {
    if (chars[i] !== '?') continue;

    if (chars[i - 1] !== 'a' && chars[i + 1] !== 'a') {
      chars[i] = 'a';
    } else if (chars[i - 1] !== 'b' && chars[i + 1] !== 'b') {
      chars[i] = 'b';
    } else {
      chars[i] = 'c';
    }
  }

  return chars.join('');
};