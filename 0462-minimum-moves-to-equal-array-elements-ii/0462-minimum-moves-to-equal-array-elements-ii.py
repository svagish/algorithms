class Solution:
    def minMoves2(self, nums: list[int]) -> int:
        steps = 0
        nums.sort()
        median = nums[len(nums)//2]
        for ele in nums:
            steps += abs(ele-median)
        return steps