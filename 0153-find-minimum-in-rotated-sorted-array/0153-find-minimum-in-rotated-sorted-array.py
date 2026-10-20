class Solution:
    def findMin(self, nums: list[int]) -> int:
        si, ei, = 0, len(nums)-1
        while (si < ei):
            mid = si +(ei-si)//2
            if (nums[mid] > nums[ei]): si = mid+1
            else: ei =  mid
        return nums[si]

        