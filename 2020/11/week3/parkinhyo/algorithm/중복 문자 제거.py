global count

class Solution:
    def __init__(self):
        global count
        count = 1


    def removeDuplicateLetters(self, s: str) -> str:
        global count
        print()
        print(f'{count}번째 재귀함수')
        print(f's = {s}')
        count += 1
        tmp = 1
        for char in sorted(set(s)):
            print(f'{tmp}번째 for문 sorted(set(s) : {sorted(set(s))}')
            tmp += 1
            suffix = s[s.index(char):]
            print(f'char = {char}')
            print(f'suffix = {suffix}')
            print(f'set(s) = {set(s)}')
            print(f'set(suffix) = {set(suffix)}')

            if set(s) == set(suffix):
                return char + self.removeDuplicateLetters(suffix.replace(char, ''))

        return ''


# answer = Solution().removeDuplicateLetters("cbacdcbc")
answer = Solution().removeDuplicateLetters("ebcabc")
print()
print()
print(answer)