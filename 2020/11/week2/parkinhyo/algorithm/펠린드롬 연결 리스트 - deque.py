import collections
from typing import Deque

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution():
    def isPalindrome(self, head: ListNode) -> bool:
        q: Deque = collections.deque()

        if not head:
            return True

        node = head
        while node is not None:
            q.append(node.val)
            node = node.next

        while len(q) > 1:
            if q.popleft() != q.pop():
                return False

        return True

