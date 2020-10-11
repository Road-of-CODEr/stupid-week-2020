/**
 * @param {number[][]} points
 * @return {number}
 */
const minCostConnectPoints = (points) => {
  const n = points.length;
  if (n < 2) return 0;
  
  const dists = [];
  for (let i = 0; i < n; i++) {
    for (let j = i+1; j < n; j++) {
      const d = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
      dists.push([d, i, j]);
    }
  }
  dists.sort((a,b) => b[0] - a[0]);
  
  const parent = Array(n).fill(null).map((cur, i) => i);
  const find = (e) => {
    if (parent[e] !== e) {
      const p = find(parent[e]);
      parent[e] = p;
    }
    return parent[e];
  }
  const union = (a, b) => {
    const pa = find(a);
    const pb = find(b);
    parent[pb] = pa;
  }
  
  let res = 0, remain = n - 1;
  while (remain > 0 && dists.length > 0) {
    const [d, a, b] = dists.pop();
    if (find(a) !== find(b)) {
      res += d;
      union(a, b);
      remain--;
    }
  }
  return res;
};