
# class Solution:
#     def __init__(self):
#         self.count = 1

#     def removeDuplicateLetters(self, s: str) -> str:
#         counter, stack = collections.Counter(s), []
#         print(f'counter : {counter}')
#         for char in s:
#             print()
#             print(f'{self.count}번째 for문')
#             self.count += 1
#             print(f'char : {char}')
#             counter[char] = -1
#             if char in stack:
#                 print("char in stack")
#                 continue

#             while stack and char < stack[-1] and counter[stack[-1]] > 0:
#                 print("While문")
#                 print(f'stack[-1] : {stack[-1]}')
#                 stack.pop()

#             stack.append(char)

#         print()
#         return ''.join(stack)


# print(f'answer : {Solution().removeDuplicateLetters("cbacdcbc")}')
import collections

class Solution:
    def removeDuplicateLetters(self, s: str) -> str:
        counter, stack = collections.Counter(s), []

        for char in s:
            counter[char] -= 1
            if char in stack:
                continue

            while stack and char < stack[-1] and counter[stack[-1]] > 0:
                stack.pop()

            stack.append(char)

        return ''.join(stack)


# class Solution:
#     def removeDuplicateLetters(self, s: str) -> str:
#         counter, seen, stack = collections.Counter(s), set(), []

#         for char in s:
#             counter[char] -= 1
#             if char in seen:
#                 continue
#             # 뒤에 붙일 문자가 남아 있다면 스택에서 제거
#             while stack and char < stack[-1] and counter[stack[-1]] > 0:
#                 seen.remove(stack.pop())
#             stack.append(char)
#             seen.add(char)

#         return ''.join(stack)

print(Solution().removeDuplicateLetters("cbacdcbc"))