class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


def my_print(l1: ListNode):
    print("l1 : ", end="")
    while l1 is not None:
        print(f'{l1.val} -> ', end="")
        l1 = l1.next
    print()


class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:
        root = prev = ListNode(None)
        prev.next = head  # fixme [1] 이런식으로 들어오는것을 대비하기 위해 필요
        while head and head.next:
            # fixme b가 a(head)를 가리키도록 할당
            b = head.next
            head.next = b.next
            b.next = head

            # fixme prev가 b를 가리키도록 할당
            prev.next = b

            # fixme 다음번 비교를 위해 이동
            head = head.next
            prev = prev.next.next  # fixme 3, 4가 4, 3으로 바꼈을 때 1, 2 쪽의 2를 4와 연결시키기 위해서는 prev가 꼭 필요하고 매 반복마다 순간 바꿔주어야 한다.

            my_print(root)

        return root.next

l1 = ListNode(1)
l1.next = ListNode(2)
l1.next.next = ListNode(3)
l1.next.next.next = ListNode(4)

s = Solution()
s.swapPairs(l1)
