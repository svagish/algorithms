class Solution {
    int n;
    long[] pa;
    long[] dp;
    long m;

    public long hashQuery(int l, int r) {
        long ans = dp[r];
        if (l > 0) ans = (ans - dp[l-1] + m) % m;
        return ans;
    }
    
    public int distinctEchoSubstrings(String text) {
        m = (long)1e9 + 7;
        n = text.length();
        pa = new long[n];
        dp = new long[n];
        pa[0] = 1;
        dp[0] = text.charAt(0)-'a'+1;
        long p = 31;
        long pow = 31;

        for (int i = 1; i < n; i++) {
            pa[i] = pow;
            dp[i] = (dp[i-1] + (text.charAt(i)-'a'+1) * pa[i]) % m;
            pow = (pow * p) % m;
        }

        Set<Long> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j+=2) {
                int len = j-i+1;
                int mid = i + len/2;
                long hash1 = hashQuery(i, mid-1);
                long hash2 = hashQuery(mid, j);
                if ((hash1 * pa[mid]) % m == (hash2 * pa[i]) % m) {
                    // normalize j to end before adding in set
                    set.add((hashQuery(i, j) * pa[n-j-1])%m);
                }
            }
        }

        return set.size();
    }
}