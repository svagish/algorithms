class Solution {
    public long[] costSlidingWindow(int[] nums, int k) {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        
        int leftCount = 0;
        int rightCount = 0;

        long leftSum = 0;
        long rightSum = 0;

        int i = 0;
        int n = nums.length;
        long[] ans = new long[n-k+1];
        
        for (int j = 0; j < n; j++) {
            int ele = nums[j];

            if (leftCount <= rightCount) {
                left.put(ele, left.getOrDefault(ele, 0) + 1);
                leftCount++;
                leftSum += ele;
            }
            else {
                right.put(ele, right.getOrDefault(ele, 0) + 1);
                rightCount++;
                rightSum += ele;
            }

            Map.Entry<Integer, Integer> leftEntry = left.lastEntry();
            Map.Entry<Integer, Integer> rightEntry = right.firstEntry(); 
            
            // balancing
            if (leftEntry != null && rightEntry != null) {
                int leftMax = leftEntry.getKey();
                int rightMin = rightEntry.getKey();

                // left max > right min, swap
                if (leftMax > rightMin || leftCount < rightCount) {
                    // remove leftMax from left
                    if (leftEntry.getValue() == 1) left.remove(leftMax);
                    else left.put(leftMax, leftEntry.getValue()-1);
                    leftSum -= leftMax;

                    // remove rightMin from right
                    if (rightEntry.getValue() == 1) right.remove(rightMin);
                    else right.put(rightMin, rightEntry.getValue()-1);
                    rightSum -= rightMin;
                    
                    // add rightMin in left
                    left.put(rightMin, left.getOrDefault(rightMin, 0) + 1); 
                    leftSum += rightMin;
                    
                    // add leftMax in right
                    right.put(leftMax, right.getOrDefault(leftMax, 0) + 1);
                    rightSum += leftMax;
                }

            }

            // reduce window size
            if (j-i+1 == k) {
                int median = (((k & 1) == 0) ? (left.lastKey() + right.firstKey())/2 : left.lastKey());
                ans[i] = ((long)median*(long)leftCount - leftSum) + (rightSum - (long)median*(long)rightCount);

                if(left.containsKey(nums[i])) {
                    int freq = left.get(nums[i]);
                    if (freq == 1) left.remove(nums[i]);
                    else left.put(nums[i], freq-1); 
                    leftCount--;
                    leftSum -= nums[i];
                }
                else {
                    int freq = right.get(nums[i]);
                    if (freq == 1) right.remove(nums[i]);
                    else right.put(nums[i], freq-1);
                    rightCount--;
                    rightSum -= nums[i];
                }
                i++;
            }
        }

        return ans;
    }

    public long rec(long[] cost, Long[][] dp, int i, int x, int k) {
        if (k == 0) return 0;
        if (i >= cost.length) return Long.MAX_VALUE;

        if (dp[i][k] != null) return dp[i][k];

        long pick = rec(cost, dp, i+x, x, k-1);
        long skip = rec(cost, dp, i+1, x, k);
        if (pick != Long.MAX_VALUE) pick += cost[i];

        return dp[i][k] = Math.min(pick, skip);
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = costSlidingWindow(nums, x);
        Long[][] dp = new Long[cost.length][k+1];
        return rec(cost, dp,  0, x, k);
    }
}