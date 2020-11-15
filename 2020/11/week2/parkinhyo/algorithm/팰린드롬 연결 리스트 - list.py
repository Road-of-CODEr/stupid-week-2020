from typing import List

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution():
    def isPalindrome(self, head: ListNode) -> bool:
        q: List = []

        if not head:
            return True

        node = head
        while node is not None:
            q.append(node.val)
            node = node.next


        while len(q) > 1:
            if q.pop(0) != q.pop():
                return False

        return True



if __name__ == '__main__':

    head = ListNode(1)
    head.next = ListNode(2)
    head.next.next = ListNode(2)
    head.next.next.next = ListNode(1)

    print(Solution().isPalindrome2(head))



