class Solution:
    def minOperations(self, s: str) -> int:
        s_sorted, min_char, max_char, n = ''.join(sorted(s)), min(s), max(s), len(s)

        # abc, aa
        if s == s_sorted: return 0
        # gf
        elif n == 2: return -1
        # dog
        elif min_char == s[0] or max_char == s[n-1]: return 1
        # jgg, jjg
        elif min_char in s[:n-1] or max_char in s[1:]: return 2
        # edc, ywxv
        else: return 3