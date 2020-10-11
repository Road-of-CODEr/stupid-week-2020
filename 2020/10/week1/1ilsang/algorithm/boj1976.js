// https://www.acmicpc.net/problem/1976
const fs = require('fs');

const [nParams, mParams, ...params] = fs.readFileSync('/dev/stdin').toString().split('\n');

const n = parseInt(nParams);
const m = parseInt(mParams);
const plans = params[n].split(' ').map(e => parseInt(e) - 1);
const parents = [...Array(n)].map((cur, index) => index);

const find = (node) => {
  if(parents[node] !== node) parents[node] = find(parents[node]);
  return parents[node];
}

const union = (a, b) => {
  parents[find(a)] = find(b);
}

for(let i = 0; i < n; i++) {
  const row = params[i].split(' ');
  for(let j = 0; j < i; j++) {
    if(row[j] !== '1') continue;
    union(i, j);
  }
}

const roots = plans.map(e => find(e));
const answer = roots.every(e => e === roots[0]) ? 'YES' : 'NO';

console.log(answer);
