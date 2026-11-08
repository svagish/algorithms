class Solution:
    def countServers(self, grid: list[list[int]]) -> int:
        m, n = len(grid), len(grid[0])
        vis = [[False for _ in range(n)] for _ in range(m)]
        total = 0

        for i in range(m):
            count = sum(grid[i])
            if count > 1:
                total += count
                vis[i] = [grid[i][j] == 1 for j in range(n)]
            
        for j in range(n):
            count, vis_count = 0, 0
            for i in range(m):
               if vis[i][j]: 
                vis_count += 1
               elif grid[i][j] == 1:
                count += 1
            if count + vis_count > 1:
                total += count

        return total


