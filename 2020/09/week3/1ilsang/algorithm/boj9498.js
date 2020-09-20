// https://www.acmicpc.net/problem/9498
const fs = require('fs');

const n = parseInt(fs.readFileSync('/dev/stdin').toString());

const answer = n >= 90 ? 'A' : n > 79 ? 'B' : n > 69 ? 'C' : n > 59 ? 'D' : 'F';

console.log(answer);