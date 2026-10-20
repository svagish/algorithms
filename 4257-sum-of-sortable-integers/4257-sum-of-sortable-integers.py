from math import isqrt

class Solution:
    def search_kmp(self, pattern: list[int], text: list[int]) -> bool:
        m = len(pattern)
        lps = [0] * m
        j = 0
        for i in range(1, m):
            while j and pattern[i] != pattern[j]:
                j = lps[j - 1]
            if pattern[i] == pattern[j]:
                j += 1
            lps[i] = j

        j = 0
        for c in text:
            while j and c != pattern[j]:
                j = lps[j - 1]
            if c == pattern[j]:
                j += 1
            if j == m:
                return True
        return False

    def sortableIntegers(self, nums: list[int]) -> int:
        n = len(nums)
        nums_sorted = sorted(nums)
        ans = 0
        for d in range(1, isqrt(n) + 1):
            if n % d:
                continue
            for k in {d, n // d}:
                # check for both smaller(d) and bigger(n//d) factor
                if all(
                    self.search_kmp(nums_sorted[s:s + k], nums[s:s + k] * 2)
                    for s in range(0, n, k)
                ):
                    ans += k
        return ans