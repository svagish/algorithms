import heapq

class Solution:
    def minPathSum(self, grid: list[list[int]]) -> int:
        INF = 10**9
        m, n = len(grid), len(grid[0])
        dij = [[INF for _ in range(n)] for _ in range(m)]
        dij[0][0] = grid[0][0]
        dirs = [[1,0], [0,1]]
        pq = []
        heapq.heappush(pq, (grid[0][0], 0, 0))
        ans = INF

        while pq:
            wsf, i, j = heapq.heappop(pq)
            if (i == m-1 and j == n-1):
                ans = wsf
                break

            for di, dj in dirs:
                nbr_i, nbr_j = i + di, j + dj
                if (nbr_i >= 0 and nbr_j >= 0 and nbr_i < m and nbr_j < n 
                    and wsf + grid[nbr_i][nbr_j] < dij[nbr_i][nbr_j] 
                ):
                    dij[nbr_i][nbr_j] = wsf + grid[nbr_i][nbr_j]
                    heapq.heappush(pq, (dij[nbr_i][nbr_j], nbr_i, nbr_j))

        return ans

        