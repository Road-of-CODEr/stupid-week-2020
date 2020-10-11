/**
 * https://leetcode.com/contest/weekly-contest-206/problems/special-positions-in-a-binary-matrix
 * @param {number[][]} mat
 * @return {number}
 */
const numSpecial = (mat) => {
  let answer = 0;
  for(let r = 0; r < mat.length; r++) {
    for(let c = 0; c < mat[r].length; c++) {
      if(mat[r][c]) answer += go(mat, r, c);
    }
  }
  return answer;
};

const dr = [0, 1, 0, -1];
const dc = [1, 0, -1, 0];
const go = (mat, r, c) => {
  let isWrong = false;
  for(let i = 0; i < 4; i++) {
    let nr = r + dr[i];
    let nc = c + dc[i];
    while(true) {
      if(nr < 0 || nc < 0 || nr >= mat.length || nc >= mat[r].length) break;
      if(mat[nr][nc]) {
        isWrong = true;
        break;
      }
      nr += dr[i];
      nc += dc[i];
    }
    if(isWrong) break;
  }
  return isWrong ? 0 : 1;
}