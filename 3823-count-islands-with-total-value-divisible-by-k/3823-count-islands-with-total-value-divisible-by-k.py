from collections import deque
class Solution:
    def countIslands(self, grid: List[List[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        vis = [[False for _ in range(n)] for _ in range(m)]
        dirs = [[-1,0], [0,-1], [1,0], [0,1]]
        q = deque()
        ans = 0

        def BFS() -> bool:
            s = 0
            while q:
                i, j = q.popleft()
                s += grid[i][j]
                for di, dj in dirs:
                    nbr_i, nbr_j = i + di, j + dj

                    if (nbr_i >= 0 and nbr_j >= 0 and nbr_i < m and nbr_j < n 
                        and grid[nbr_i][nbr_j] != 0 and not vis[nbr_i][nbr_j]):
                            vis[nbr_i][nbr_j] = True
                            q.append([nbr_i, nbr_j])
            
            return s % k == 0


        for i in range(m):
            for j in range(n):
                if (grid[i][j] != 0 and not vis[i][j]):
                    vis[i][j] = True
                    q.append([i, j])
                    ans += 1 if (BFS()) else 0 # ans += BFS()
            
        return ans 
        


