// https://www.acmicpc.net/problem/15552
const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

let answer = '';
rl.on('line', line => {
  const input = line.split(' ');
  if(input.length !== 2) return;
  
  answer += parseInt(input[0])+parseInt(input[1]) + '\n';
}).on('close', () => {
  console.log(answer);
  process.exit();
});