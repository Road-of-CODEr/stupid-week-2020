import collections


def correct_one(p):
    stack = []
    for i in range(len(p)):
        if p[i] == '(':
            stack.append('(')
        else:
            if not stack:
                return False
            stack.pop()
    return len(stack) == 0


def balanced_one(p):
    counter = collections.Counter(p)
    print(counter.keys())
    counter.most_common(0)
    return counter['('] == counter[')']


def solution(p):
    if not p:
        return ''

    for i in range(2, len(p) + 1, 2):
        u, v = p[0:i], p[i:len(p)]
        if balanced_one(u) and balanced_one(v):
            if correct_one(u):
                return u + solution(v)
            else:
                text = u[1:len(u) - 1]
                append_text = ''.join(')' if text[i] == '(' else '(' for i in range(len(text)))
                return '(' + solution(v) + ')' + append_text

