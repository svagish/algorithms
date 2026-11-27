class Solution {
    int n;
    int[][] items;
    int[] freeCount;
    int[][][] dp;

    int rec(int i, int budget, int bought) {
        if (i == n || budget <= 0) return 0;
        if (dp[bought][i][budget] != -1) return dp[bought][i][budget];

        int best = rec(i + 1, budget, 0); // skip item i

        int cost = items[i][1];
        if (cost <= budget) {
            int gain = (bought == 0) ? 1 + freeCount[i] : 1;
            best = Math.max(best, gain + rec(i, budget - cost, 1));
        }
        return dp[bought][i][budget] = best;
    }

    public int maximumSaleItems(int[][] items, int budget) {
        this.items = items;
        n = items.length;
        freeCount = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j && items[j][0] % items[i][0] == 0) freeCount[i]++;

        dp = new int[2][n][budget + 1];
        for (int[][] a : dp) for (int[] b : a) Arrays.fill(b, -1);

        return rec(0, budget, 0);
    }
}