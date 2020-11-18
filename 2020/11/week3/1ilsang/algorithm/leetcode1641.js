/**
 * @param {number} n
 * @return {number}
 */
const countVowelStrings = (n) => {
  const str = "aeiou";
  let res = 0;

  const backtrack = (i, arr) => {
    if (arr.length == n) {
      res++;
      return;
    }

    for (let j = i; j < str.length; j++) {
      arr.push(j);
      backtrack(j, arr);
      arr.pop();
    }
  };

  backtrack(0, []);
  return res;
};
