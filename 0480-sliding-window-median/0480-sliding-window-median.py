from sortedcontainers import SortedDict
class Solution:
    def medianSlidingWindow(self, nums: list[int], k: int) -> list[float]:
        n = len(nums)
        left, right = SortedDict(), SortedDict()
        ans = [0 for _ in range(n-k+1)]
        leftCount, rightCount = 0, 0
        i = 0

        for j in range(n):
            ele = nums[j]
            
            # expand
            if (leftCount <= rightCount):
                left[ele] = left.get(ele, 0) + 1
                leftCount += 1
            else:
                right[ele] = right.get(ele, 0) + 1
                rightCount += 1        

            # if leftMax > rightMin or right size > left size, swap these
            if leftCount > 0 and rightCount > 0 and (left.peekitem(-1)[0] > right.peekitem(0)[0] or len(right) > len(left)):
                lk, lv = left.peekitem(-1)
                rk, rv = right.peekitem(0)
                
                if lv == 1: left.popitem(-1)
                else: left[lk] = lv-1
                left[rk] = left.get(rk, 0) + 1

                if rv == 1: right.popitem(0)
                else: right[rk] = rv-1
                right[lk] = right.get(lk, 0) + 1
                                
            # shrink (remove nums[i] from window)
            if j-i+1 == k:
                median = left.peekitem(-1)[0] if (leftCount + rightCount) & 1 else left.peekitem(-1)[0]/2 + right.peekitem(0)[0]/2 
                ans[i] = median
                if nums[i] in left:
                    val = left[nums[i]]
                    if val == 1: left.pop(nums[i])
                    else: left[nums[i]] = val-1
                    leftCount -= 1
                else:
                    val = right[nums[i]]
                    if val == 1: right.pop(nums[i])
                    else: right[nums[i]] = val-1
                    rightCount -= 1
                i += 1

        return ans

