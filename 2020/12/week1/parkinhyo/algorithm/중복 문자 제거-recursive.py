# import pprint

class Solution:
    def __init__(self):
        self.count = 0
        
    def removeDuplicateLetters(self, s: str) -> str:
        
        print()
        print(f'{self.count}번째 재귀함수')
        print(f's = {s}')
        self.count += 1
        tmp = 1
        for char in sorted(set(s)):
            print(f'{tmp}번째 for문 sorted(set(s)) : {sorted(set(s))}')
            tmp += 1
            suffix = s[s.index(char):]
            print(f'char = {char}')
            print(f'suffix = {suffix}')
            print(f'set(s) = {set(s)}')
            print(f'set(suffix) = {set(suffix)}')

            if set(s) == set(suffix):
                return char + self.removeDuplicateLetters(suffix.replace(char, ''))

        return ''
    
answer1 = Solution().removeDuplicateLetters("bcabc")
print(f'answer : {answer1}')

answer2 = Solution().removeDuplicateLetters("cbacdcbc")
print(f'answer : {answer2}')

answer3 = Solution().removeDuplicateLetters("ebcabc")
print(f'answer : {answer3}')

answer4 = Solution().removeDuplicateLetters("ebcabce")
print(f'answer : {answer4}')


