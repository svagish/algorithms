class Solution {
    public int rec(int i, int[] costs, int[] dp) {
        if (i == costs.length-1) return dp[i] = 0;
        if (dp[i] != -1) return dp[i];

        int csf = (int)1e9;

        int j1 = i + 1;
        int j2 = i + 2;
        int j3 = i + 3;

        if (j1 < costs.length) csf = Math.min(csf, rec(j1, costs, dp) + costs[j1] + (j1-i) * (j1-i));
        if (j2 < costs.length) csf = Math.min(csf, rec(j2, costs, dp) + costs[j2] + (j2-i) * (j2-i));
        if (j3 < costs.length) csf = Math.min(csf, rec(j3, costs, dp) + costs[j3] + (j3-i) * (j3-i));

        return dp[i] = csf;
    }

    public int climbStairs(int n, int[] costs) {
        int[] cost = new int[n+1];
        for (int i = 0; i < n; i++) cost[i+1] = costs[i];
        int[] dp = new int[cost.length];
        Arrays.fill(dp, -1);
        return rec(0, cost, dp);
    }
}