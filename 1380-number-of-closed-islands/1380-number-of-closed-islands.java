class Solution {
    int m, n;
    int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
    Queue<int[]> qu = new LinkedList<>();
    boolean[][] vis;
    int[][] grid;

    public boolean BFS() {
        boolean check = true;
        while (!qu.isEmpty()) {
            int[] src = qu.poll();
            int i = src[0], j = src[1];
            for (int[] d : dir) {
                int ni = i + d[0], nj = j + d[1];
                if (ni >= 0 && nj >= 0 && ni < m && nj < n) {
                    if (!vis[ni][nj] && grid[ni][nj] == 0) {
                        if (ni == 0 || nj == 0 || ni == m-1 || nj == n-1) {
                            check = false;
                        } 
                        vis[ni][nj] = true;
                        qu.offer(new int[]{ni, nj});
                    }
                }
            }
        }
        return check;
    }

    public int closedIsland(int[][] g) {
        this.grid = g;
        this.m = grid.length;
        this.n = grid[0].length;
        this.vis = new boolean[m][n];
        int ans = 0;

        for (int i = 1; i < m-1; i++) {
            for (int j = 1; j < n-1; j++) {
                if (grid[i][j] == 0 && !vis[i][j]) {
                    vis[i][j] = true;
                    qu.offer(new int[]{i, j});
                    if (BFS()) ans++;
                }
            }
        }
        return ans;
    }
}