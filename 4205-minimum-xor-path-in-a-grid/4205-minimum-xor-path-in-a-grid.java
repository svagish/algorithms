class Solution {
    public boolean[] dfs(int[][] grid, boolean[][][] dp, int i, int j, int m, int n) {
        if (i >= m || j >= n) return new boolean[1024];
        if (i == m-1 && j == n-1) {
            boolean[] temp = new boolean[1024];
            temp[grid[i][j]] = true;
            dp[i][j] = Arrays.copyOf(temp, 1024);
            return temp;
        }

        if (dp[i][j] != null) return dp[i][j];

        boolean[] down = null;
        boolean[] right = null;
        if (i + 1 < m) down = dfs(grid, dp, i+1, j, m, n);                      
        if (j + 1 < n) right = dfs(grid, dp, i, j+1, m, n);
        
        int curr = grid[i][j];
        boolean[] res = new boolean[1024];
        for (int k = 0; k < 1024; k++) {
            if ((down != null && down[k]) || (right != null && right[k])) res[k^curr] = true;
        }

        dp[i][j] = Arrays.copyOf(res, 1024);
        return res;
    }

    public int minCost(int[][] grid) {
        boolean[][][] dp = new boolean[grid.length][grid[0].length][];
        boolean[] rec = dfs(grid, dp, 0, 0, grid.length, grid[0].length);
        for (int i = 0; i < 1024; i++) {
            if (rec[i]) return i;
        }
        return -1;
    }
}