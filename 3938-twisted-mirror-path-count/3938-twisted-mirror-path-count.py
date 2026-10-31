class Solution:
    def uniquePaths(self, grid: List[List[int]]) -> int:
        # down - 0, right - 1
        MOD = 10**9 + 7
        m, n = len(grid), len(grid[0])
        dp = [[[-1]*2 for _ in range(n)] for _ in range(m)]

        def dfs(i, j, p) -> int:
            if (i >= m or j >= n):
                return 0

            if (i == m-1 and j == n-1):
                dp[i][j][p] = 1
                return 1

            if (dp[i][j][p] != -1):
                return dp[i][j][p]
            
            count = 0
            if (grid[i][j] == 1):
                if (p == 0):
                    count = dfs(i, j+1, 1)
                else: 
                    count = dfs(i+1, j, 0) 
            else:
                count = (count + dfs(i+1, j, 0)) % MOD
                count = (count + dfs(i, j+1, 1)) % MOD

            dp[i][j][p] = count
            return count

        return dfs(0, 0, 0)
