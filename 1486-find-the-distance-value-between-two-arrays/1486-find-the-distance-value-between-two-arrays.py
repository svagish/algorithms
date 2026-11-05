class Solution:
    def findTheDistanceValue(self, arr1: list[int], arr2: list[int], d: int) -> int:
        count = 0
        arr2.sort()
        n = len(arr2)

        def search(target):
            si, ei = 0, n-1
            while si + 1 <= ei:
                mid = si + (ei-si)//2
                if arr2[mid] < target: si = mid + 1
                else: ei = mid
            return si

        for a in arr1:
            target = a-d
            idx = search(target)
            if (idx < n and arr2[idx] >= a-d and arr2[idx] <= a+d): continue
            count += 1
            
        return count
        