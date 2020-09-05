from typing import List
from collections import Counter


class Solution:
    def findLeastNumOfUniqueInts(self, arr: List[int], k: int) -> int:
        counter = Counter(arr)
        number_count_array = sorted(counter.values(), reverse=True)
        answer = len(number_count_array)
        while k > 0 and number_count_array:
            if number_count_array[answer - 1] <= k:
                k -= number_count_array[answer - 1]
                answer -= 1
                number_count_array.pop()
            else:
                break
        return answer


