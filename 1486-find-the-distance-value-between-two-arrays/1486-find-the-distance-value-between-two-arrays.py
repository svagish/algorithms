class Solution:
    def findTheDistanceValue(self, arr1: list[int], arr2: list[int], d: int) -> int:
        count = 0
        for i in range(len(arr1)):
            check = False
            for j in range(len(arr2)):
                if (abs(arr1[i]-arr2[j]) <= d):
                    check = True
                    break
            if not check:
                count += 1
        return count
        