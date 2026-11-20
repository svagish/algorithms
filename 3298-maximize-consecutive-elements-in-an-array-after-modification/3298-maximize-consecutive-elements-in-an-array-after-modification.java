class Solution {

    public int lowerBound(int from, int target, int[] nums) {
        int lo = from, hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) lo = mid + 1;  
            else hi = mid;                          
        }
        return lo;
    }

    public int search(int from, int target, int[] nums) {
        int idx = lowerBound(from, target, nums);
        return (idx < nums.length && nums[idx] == target) ? idx : -1;
    }

    public int rec(int i, int curr, int[] nums, int[][] dp) {
        int r = curr - nums[i]; // r = 0, did not +1, r = 1, did +1
        if (dp[i][r] != 1) return dp[i][r];
        int best = 1;
        // search curr
        int j = search(i + 1, curr, nums);
        // found curr, inc for next search
        if (j != -1) {
            best = Math.max(best, 1 + rec(j, nums[j]+1, nums, dp));
        }
        // search curr+1
        j = search(i + 1, curr + 1, nums);
        // found curr+1
        if (j != -1) {
            best = Math.max(best, 1 + rec(j, nums[j], nums, dp));
        }
        return dp[i][r] = best;
    }

    public int maxSelectedElements(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int result = 1;
        int[][] dp = new int[n][2];
        for (int[] d : dp) Arrays.fill(d, 1);
        for (int i = 0; i < n; i++) {
            result = Math.max(result, Math.max(rec(i, nums[i], nums, dp), rec(i, nums[i] + 1, nums, dp)));
        }
        return result;
    }
}