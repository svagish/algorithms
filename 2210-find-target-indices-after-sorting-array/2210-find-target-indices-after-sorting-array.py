class Solution:
    def targetIndices(self, nums: list[int], target: int) -> list[int]:
        lessThan: int = 0
        equal: int = 0
        for i in range(len(nums)):
            if nums[i] < target:
                lessThan += 1
            elif nums[i] == target:
                equal += 1
        ans = []
        idx: int = 0
        idx += lessThan
        while equal > 0:
            ans.append(idx)
            idx += 1
            equal -= 1
        return ans


        