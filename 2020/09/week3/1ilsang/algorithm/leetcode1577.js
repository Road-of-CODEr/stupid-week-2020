/**
 * https://leetcode.com/problems/number-of-ways-where-square-of-number-is-equal-to-product-of-two-numbers/
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
const sortBy = require('lodash/sortBy')
const sortedIndexOf = require('lodash/sortedIndexOf')
const sortedLastIndexOf = require('lodash/sortedLastIndexOf')

const getNum = (sorted1, sorted2) => {
  let result = 0

  for (const num1 of sorted1) {
    const squared = num1 * num1

    for (let aIndex = 0; aIndex < sorted2.length; aIndex++) {
      const a = sorted2[aIndex]
      const b = squared / a

      const bIndex = sortedIndexOf(sorted2, b)
      const bLastIndex = sortedLastIndexOf(sorted2, b)

      if (aIndex <= bLastIndex) {
        const count = (bLastIndex - Math.max(aIndex + 1, bIndex) + 1)
        result += count
      }
    }
  }

  return result
}

/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
const numTriplets = function (nums1, nums2) {
  const sorted1 = sortBy(nums1)
  const sorted2 = sortBy(nums2)

  return getNum(sorted1, sorted2) + getNum(sorted2, sorted1)
}