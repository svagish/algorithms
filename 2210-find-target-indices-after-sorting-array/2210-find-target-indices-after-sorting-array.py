class Solution:
    def targetIndices(self, nums: list[int], target: int) -> list[int]:
        less = sum(ele < target for ele in nums)
        equal = nums.count(target)
        return list(range(less, less+equal))

        