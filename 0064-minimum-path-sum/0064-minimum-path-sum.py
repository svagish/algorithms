class Solution:
    def minPathSum(self, grid: list[list[int]]) -> int:
        INF = 10**9
        m, n = len(grid), len(grid[0])
        dp = [[INF for _ in range(n)] for _ in range(m)]
        dirs = [[1,0], [0,1]]
        
        def dfs(i, j) -> int:
            if (i == m-1 and j == n-1):
                dp[i][j] = grid[i][j]
                return dp[i][j]

            if (dp[i][j] != INF):
                return dp[i][j]
                
            rec_ans = INF
            for di, dj in dirs:
                nbr_i, nbr_j = i + di, j + dj
                if (nbr_i >= 0 and nbr_j >= 0 and nbr_i < m and nbr_j < n):
                    rec_ans = min(rec_ans, dfs(nbr_i, nbr_j))

            dp[i][j] = rec_ans + grid[i][j] 
            return dp[i][j]

        return dfs(0,0)

        