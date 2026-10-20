class Solution:
    def check(self, nums: list[int]) -> bool:
        isRotated = False
        for i in range(len(nums)-1):
            if nums[i] > nums[i+1]:
                if isRotated: return False
                isRotated = True
        return True if not isRotated else isRotated and nums[0] >= nums[len(nums)-1]