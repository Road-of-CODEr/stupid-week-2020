/**
 * @param {string} s
 * @param {number} a
 * @param {number} b
 * @return {string}
 */
const findLexSmallestString = (s, a, b) => {
  // const result = minForThis(s, a);
  // console.log(result);

  const all = [];
  for (let i = 0; i <= 2 * s.length + 1; ++i) {
    let min = minForThis(s, a, 1);
    all.push(min);
    if (b % 2 == 1) {
      min = minForThis(s, a, b);
      all.push(min);
    }
    s = min.slice(b) + min.slice(0, b);
    // console.log(min, s);
  }

  return all.sort()[0];
};

const va = "0".charCodeAt(0);

function minForThis(s, a, i) {
  if (s.length < 2) {
    return s;
  }
  const first = gc(s, i);
  const d = gcd(a, 10);
  const todo1 = first % d;
  const todo2 = 10 + (first % d) - d;
  let factors =
    todo1 <= todo2 ? (first - (first % d)) / d : 1 + (first - (first % d)) / d;
  const done = Array.from(s)
    .map((e, i) => {
      if (i % 2 == 0) {
        return e;
      }
      const value = gc(e, 0);
      const newValue = (((value - d * factors) % 10) + 10) % 10;

      return String.fromCharCode(newValue + va);
    })
    .join("");

  return done;
}

function gcd(a, b) {
  if (b === 0) {
    return a;
  }

  return gcd(b, a % b);
}

function gc(s, i) {
  return s.charCodeAt(i) - va;
}
