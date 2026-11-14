class Solution:
    def minimumSumSubarray(self, nums: List[int], l: int, r: int) -> int:
        n = len(nums)
        cumSum = [0 for _ in range(n)]
        cumSum[0] = nums[0]
        for i in range(1, n):
            cumSum[i] = cumSum[i-1] + nums[i]
        minSum = float('inf')

        while l <= r:
            for i in range(l-1, n):
                winSum = cumSum[i] - (cumSum[i-l] if i-l >= 0 else 0)
                if (winSum > 0):
                    minSum = min(minSum, winSum)
            l += 1

        return minSum if minSum != float('inf') else -1
        