/**
 * 1599. Maximum Profit of Operating a Centennial Wheel
 * https://leetcode.com/contest/weekly-contest-208/problems/maximum-profit-of-operating-a-centennial-wheel/
 * @param {number[]} customers
 * @param {number} boardingCost
 * @param {number} runningCost
 * @return {number}
 */
const SIZE = 4;
const minOperationsMaxProfit = (customers, boardingCost, runningCost) => {
  const times = [];
  for(let [idx, cur] of customers.entries()) {
    let cost = cur;
    let nextCost = 0;
    if(cur > SIZE) {
      cost = SIZE;
      nextCost = cur - SIZE;
    }
    
    if(idx === customers.length - 1) continue;
    customers[idx] = cost;
    customers[idx + 1] += nextCost;
  }
  for(let cur of customers) {
    while(true) {
      if(cur <= SIZE) {
        times.push(cur);
        break;
      }
      times.push(SIZE);
      cur -= SIZE;
    }
  }

  let maxValue = 0;
  let answer = -1;
  times.reduce((acc, cur, idx) => {
    acc += cur;
    const cost = acc * boardingCost - (idx + 1) * runningCost;
    if(idx > 305 && idx < 311) {
      console.log(idx, cost, acc);
    }
    if(maxValue < cost) {
      maxValue = cost;
      answer = idx + 1;
    }
    return acc;
  }, 0);
  
  return answer;
};