class Solution {
    public boolean[] dfs(int[][] grid, boolean[][][] dp, int i, int j, int m, int n) {
        if (i >= m || j >= n) return dp[i][j] = new boolean[1024];
        if (i == m-1 && j == n-1) {
            boolean[] temp = new boolean[1024];
            temp[grid[i][j]] = true;
            return dp[i][j] = Arrays.copyOf(temp, 1024);
        }

        if (dp[i][j] != null) return dp[i][j];

        int curr = grid[i][j];
        boolean[] res = new boolean[1024];
        boolean[] down = dfs(grid, dp, i+1, j, m, n);
        boolean[] right = dfs(grid, dp, i, j+1, m, n);
       
        for (int k = 0; k < 1024; k++) {
            if (down[k] || right[k]) res[k^curr] = true;
        }

        return dp[i][j] = Arrays.copyOf(res, 1024);
    }
    public void print(boolean[] rec) {
        for (int i = 0; i < 1024; i++) {
            if (rec[i]) {
                System.out.print(i +" ");
            }
        }
        System.out.println();
    }
    public int minCost(int[][] grid) {
        boolean[][][] dp = new boolean[grid.length+1][grid[0].length+1][];
        boolean[] rec = dfs(grid, dp, 0, 0, grid.length, grid[0].length);
        for (int i = 0; i < 1024; i++) {
            if (rec[i]) return i;
        }
        return -1;
    }
}