class Solution:
    def isHappy(self, n: int) -> bool:
        visit = set()
        while n not in visit:
            visit.add(n)
            sum_of_n = 0
            while n:
                sum_of_n += (n % 10) ** 2
                n //= 10
            n = sum_of_n
            if n == 1:
                return True
        return False


