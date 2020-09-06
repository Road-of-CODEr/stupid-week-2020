import heapq


def solution(prices):
    answer = []
    stack = []
    for idx, price in enumerate(prices):
        heapq.heappush(stack, (-price, idx))
        while stack and -stack[0][0] > price:
            pop_price, pop_idx = heapq.heappop(stack)
            answer.append((pop_idx, idx - pop_idx))
    while stack:
        pop_price, pop_idx = heapq.heappop(stack)
        answer.append((pop_idx, len(prices) - 1 - pop_idx))

    return [ans[1] for ans in sorted(answer)]


print(solution([1, 2, 3, 2, 3]))

