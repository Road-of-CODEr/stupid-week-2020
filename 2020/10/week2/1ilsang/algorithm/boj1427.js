// https://www.acmicpc.net/problem/1427
const fs = require('fs');

const n = fs.readFileSync('/dev/stdin').toString();
// const n = '2143';
// const n = '101';
// const n = '1000';
// const n = 0;
// const n = '1112211'
if(n === 0) {
  console.log(0);
  return;
}
const answer = Array.from(n).map(e => parseInt(e)).sort((a, b) => b - a).join('');

console.log(parseInt(answer));