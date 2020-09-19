from typing import List


class Solution:
    def lean(self, point, other_point):
        dy = abs(other_point[1] - point[1])
        dx = abs(other_point[0] - point[0])
        return dx / dy if dy != 0 else 101

    def isBoomerang(self, points: List[List[int]]) -> bool:
        points = list(set(tuple(point) for point in points))
        lean = []
        for i in range(len(points) - 1):
            lean.append(self.lean(points[i], points[i+1]))
        lean.append(self.lean(points[0], points[-1]))
        return len(set(lean)) != 1

