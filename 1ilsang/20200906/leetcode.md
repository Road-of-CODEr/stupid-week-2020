[1560. Most Visited Sector in a Circular Track](https://leetcode.com/contest/weekly-contest-203/problems/most-visited-sector-in-a-circular-track/)

```javascript
/**
 * @param {number} n
 * @param {number[]} rounds
 * @return {number[]}
 */
const mostVisited = (n, rounds) => {
  const [start, end] = [rounds[0], rounds[rounds.length - 1]];
  const ret = [];

  if (start <= end) for (let i = start; i <= end; i++) ret.push(i);
  else {
    for (let i = 1; i <= end; i++) ret.push(i);
    for (let i = start; i <= n; i++) ret.push(i);
  }

  return ret;
};
```

[1561. Maximum Number of Coins You Can Get](https://leetcode.com/contest/weekly-contest-203/problems/maximum-number-of-coins-you-can-get/)

```javascript
/**
 * @param {number[]} piles
 * @return {number}
 */
const maxCoins = (piles) => {
  piles.sort((a, b) => a - b);

  let answer = 0;
  for (let i = piles.length / 3; i < piles.length; i += 2) {
    answer += piles[i];
  }
  return answer;
};
```

[1562. Find Latest Group of Size M](https://leetcode.com/contest/weekly-contest-203/problems/find-latest-group-of-size-m/)

```javascript
class UnionFind {
  constructor(arr) {
    [this.parents, this.ranks] = this.init(arr);
  }

  init(arr) {
    const parents = new Map();
    const ranks = new Map();
    arr.forEach((e) => {
      parents.set(e, e);
      ranks.set(e, 1);
    });
    return [parents, ranks];
  }

  find(cur) {
    if (this.parents.get(cur) === cur) return cur;
    const parent = this.find(this.parents.get(cur));
    this.parents.set(cur, parent);
    return parent;
  }

  union(node1, node2) {
    const parent1 = this.find(node1);
    const parent2 = this.find(node2);
    if (parent1 === parent2) return true;

    const [child, parent] =
      parent1 > parent2 ? [parent2, parent1] : [parent1, parent2];
    return this.upsert(child, parent);
  }

  upsert(child, parent) {
    this.parents.set(child, parent);
    this.ranks.set(parent, this.ranks.get(child) + this.ranks.get(parent));
    return true;
  }
}

/**
 * @param {number[]} arr
 * @param {number} m
 * @return {number}
 */
const findLatestStep = (arr, m) => {
  let answer = -1;
  const uf = new UnionFind(arr);
  const visited = new Set();
  const mLengthSet = new Set();

  for (let i = 0; i < arr.length; i++) {
    const curIdx = arr[i];
    const params = {
      uf,
      arr,
      mLengthSet,
      curIdx,
    };
    visited.add(curIdx);

    if (visited.has(curIdx - 1)) {
      unionBusiness({ ...params, targetIdx: curIdx - 1 });
    }
    if (visited.has(curIdx + 1)) {
      unionBusiness({ ...params, targetIdx: curIdx + 1 });
    }

    const parent = uf.find(curIdx);
    if (uf.ranks.get(parent) === m) mLengthSet.add(parent);
    if (mLengthSet.size > 0) answer = i + 1;
  }

  return answer;
};

const unionBusiness = ({ uf, arr, mLengthSet, targetIdx, curIdx }) => {
  const targetParent = uf.find(targetIdx);
  const curParent = uf.find(curIdx);
  uf.union(targetIdx, curIdx);
  mLengthSet.delete(targetParent);
  mLengthSet.delete(curParent);
};
```
