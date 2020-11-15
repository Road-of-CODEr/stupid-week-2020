/**
 * 1630. Arithmetic Subarrays
 * https://leetcode.com/contest/weekly-contest-212/problems/arithmetic-subarrays/
 * @param {number[]} nums
 * @param {number[]} l
 * @param {number[]} r
 * @return {boolean[]}
 */
const checkArithmeticSubarrays = (nums, l, r) =>
  l.map((start, mapIdx) =>
    nums
      .slice(start, r[mapIdx] + 1)
      .sort((a, b) => a - b)
      .every((n, everyIdx, arr, diff = arr[1] - arr[0]) =>
        everyIdx < 2 ? true : n - arr[everyIdx - 1] === diff
      )
  );
