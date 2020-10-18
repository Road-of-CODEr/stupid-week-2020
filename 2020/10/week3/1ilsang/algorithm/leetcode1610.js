/**
 * 1610. Maximum Number of Visible Points
 * https://leetcode.com/contest/weekly-contest-209/problems/maximum-number-of-visible-points/
 * @param {number[][]} points
 * @param {number} angle
 * @param {number[]} location
 * @return {number}
 */
const visiblePoints = (points, angle, location) => {
  let rads = points
    .filter(([x, y]) => x !== location[0] || y !== location[1])
    .map(([x, y]) => Math.atan2(y-location[1], x-location[0])/Math.PI*180)
    .sort((a,b) => a-b);
  const origin = points.length - rads.length;
  rads = rads.concat(rads.map(e => e+360));

  let maxCount = 0, i = 0, j = 0;

  while (i < rads.length/2) {
    while (j-i >= rads.length/2 || (i <= j && rads[j]-rads[i] > angle)) {
      i++;
    }
    
    maxCount = Math.max(maxCount, j-i+1+origin);
    j++;
  }

  return maxCount;
};