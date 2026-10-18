class Solution {
    int MOD = (int)1e9+7;

    public long interleave(int i, int j, int m, int n, Long[][] dp) {
        if (i == m && j == n) return 1;
        if (dp[i][j] != null) return dp[i][j];
        long ways = 0;
        if (i < m) ways = (ways + interleave(i+1, j, m, n, dp)) % MOD;
        if (j < n) ways = (ways + interleave(i, j+1, m, n, dp)) % MOD;
        return dp[i][j] = ways;
    }

    public long rec(List<Integer> nums) {
        if (nums.size() <= 2) return 1; // 0 or 1 elements: only one way
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) left.add(nums.get(i));
            else right.add(nums.get(i));
        }
        int m = left.size(), n = right.size();
        Long[][] dp = new Long[m+1][n+1];
        long ways = interleave(0, 0, m, n, dp); // C(m+n, m)
        ways = ways % MOD * rec(left) % MOD;
        ways = ways % MOD * rec(right) % MOD;
        return ways;
    }

    public int numOfWays(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int n : nums) list.add(n);
        return (int)((rec(list) - 1 + MOD) % MOD);
    }
}