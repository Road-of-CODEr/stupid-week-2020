/**
 * https://leetcode.com/contest/weekly-contest-208/problems/maximum-number-of-achievable-transfer-requests/
 * 1601. Maximum Number of Achievable Transfer Requests
 * @param {number} n
 * @param {number[][]} requests
 * @return {number}
 */
const maximumRequests = (n, requests) => {
  const len = requests.length;
  let answer = 0;
  
  for(let i = 0; i < (1 << len); i++) {
    const arr = new Array(n).fill(0);
    let cnt = 0;
    
    for(let j = 0; j < len; j++) {
      const cur = requests[j];
      if(i & (1 << j)) {
        cnt++;
        arr[cur[0]]--;
        arr[cur[1]]++;
      }
    }
    
    let isPossible = true;
    for(let j = 0; j < arr.length; j++) {
      if(arr[j] == 0) continue;
      isPossible = false;
      break;
    }
    
    if(isPossible) answer = Math.max(answer, cnt);
  }
  
  return answer;
};