from typing import List


class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        from collections import defaultdict
        db = defaultdict(int)
        for num in nums:
            if db[num] == 1:
                return num
            db[num] += 1
