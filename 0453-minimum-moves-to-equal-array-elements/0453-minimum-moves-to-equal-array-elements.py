class Solution:
    def minMoves(self, nums: list[int]) -> int:
        steps = 0
        min_num = min(nums)
        for ele in nums:
            steps += ele-min_num
        return steps