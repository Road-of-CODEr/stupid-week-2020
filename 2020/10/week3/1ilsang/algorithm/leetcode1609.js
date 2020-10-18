/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * 1609. Even Odd Tree
 * https://leetcode.com/contest/weekly-contest-209/problems/even-odd-tree/
 * @param {TreeNode} root
 * @return {boolean}
 */
const isEvenOddTree = (root) => {
  const lasts = [];
  const helper = (p, level = 0) => {
    if (!p) {
      return true;
    }
    if ((level + p.val) % 2 === 0) {
      return false;
    }
    if (lasts[level] === undefined) {
      lasts[level] = (level % 2 === 1) ? Infinity : -1;
    }
    if ((level % 2 === 1 && lasts[level] - p.val <= 0) ||
        (level % 2 === 0 && lasts[level] - p.val >= 0)) {
      return false;
    }
    lasts[level] = p.val;
    let res = helper(p.left, level + 1);
    if (!res) {
      return res;
    }
    res = helper(p.right, level + 1);
    return res;
  };
  return helper(root);
};