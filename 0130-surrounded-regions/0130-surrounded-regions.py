from collections import deque
class Solution:
    def solve(self, board: list[list[str]]) -> None:
        m, n = len(board), len(board[0])
        dirs = [[-1,0], [0,-1], [1,0], [0,1]]
        vis = [[False for _ in range(n)] for _ in range(m)]
        q = deque()

        # mark all connected O's touching boundary 
        def BFS():
            while q:
                i, j = q.popleft()
                if (vis[i][j]): continue
                vis[i][j] = True

                for di, dj in dirs:
                    nbr_i = i + di
                    nbr_j = j + dj
                    if (nbr_i >= 0 and nbr_j >= 0 and nbr_i < m and nbr_j < n and not vis[nbr_i][nbr_j] and board[nbr_i][nbr_j] == 'O'):
                        q.append([nbr_i, nbr_j])

        for i in range(m):
            if (i == 0 or i == m-1):
                for j in range(n):
                    if (board[i][j] == 'O'):
                        q.append([i, j])
            else:
                for j in [0, n-1]:
                    if (board[i][j] == 'O'):
                        q.append([i, j])

        BFS()
                
        for i in range(m):
            for j in range(n):
                if (board[i][j] == 'O' and not vis[i][j]):
                    board[i][j] = 'X'




        