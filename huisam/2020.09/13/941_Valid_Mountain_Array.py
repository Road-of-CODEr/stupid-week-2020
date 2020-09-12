from typing import List


class Solution:
    def validMountainArray(self, A: List[int]) -> bool:
        i = 0
        n = len(A)
        if not A:
            return False

        while i != n - 1 and A[i] < A[i + 1]:
            i += 1

        if i == 0 or i == n - 1:
            return False

        while i != n - 1 and A[i] > A[i + 1]:
            i += 1

        if i != n - 1:
            return False

        return True

