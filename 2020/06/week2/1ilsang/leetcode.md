1464. [Maximum Product of Two Elements in an Array](https://leetcode.com/contest/weekly-contest-191/problems/maximum-product-of-two-elements-in-an-array/)

---

```javascript
/**
 * @param {number[]} nums
 * @return {number}
 */
const maxProduct = (nums) => {
  let m1 = 0,
    m2 = 0;
  for (const cur of nums) {
    m2 = Math.max(m2, Math.min(m1, cur));
    m1 = Math.max(m1, cur);
  }
  return (m1 - 1) * (m2 - 1);
};
```

1465. [Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts](https://leetcode.com/contest/weekly-contest-191/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/)

---

```javascript
/**
 * @param {number} h
 * @param {number} w
 * @param {number[]} horizontalCuts
 * @param {number[]} verticalCuts
 * @return {number}
 */
const maxArea = (h, w, horizontalCuts, verticalCuts) => {
  horizontalCuts.sort((a, b) => a - b);
  verticalCuts.sort((a, b) => a - b);

  const MOD = 1000000007;
  const hl = horizontalCuts.length;
  const vl = verticalCuts.length;
  let Hmax = Math.max(h - horizontalCuts[hl - 1], horizontalCuts[0]);
  let Vmax = Math.max(w - verticalCuts[vl - 1], verticalCuts[0]);
  for (let i = 1; i < Math.max(hl, vl); i++) {
    let hd = horizontalCuts[i] - horizontalCuts[i - 1];
    let vd = verticalCuts[i] - verticalCuts[i - 1];
    if (isNaN(hd)) hd = 0;
    if (isNaN(vd)) vd = 0;
    Hmax = Math.max(Hmax, hd);
    Vmax = Math.max(Vmax, vd);
  }

  return (Hmax * Vmax) % MOD;
};
```
