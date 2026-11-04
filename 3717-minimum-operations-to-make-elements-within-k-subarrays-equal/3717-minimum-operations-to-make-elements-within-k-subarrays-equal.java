class Solution {
    long INF = Long.MAX_VALUE / 4;

    public long[] costSlidingWindow(int[] nums, int k) {      // CHANGED: returns long[]
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftCount = 0;
        int rightCount = 0;

        long leftSum = 0;
        long rightSum = 0;

        int i = 0;
        int n = nums.length;
        long[] ans = new long[n - k + 1];                     // CHANGED: long[]

        for (int j = 0; j < n; j++) {
            int ele = nums[j];

            if (leftCount <= rightCount) {
                left.put(ele, left.getOrDefault(ele, 0) + 1);
                leftCount++;
                leftSum += ele;
            } else {
                right.put(ele, right.getOrDefault(ele, 0) + 1);
                rightCount++;
                rightSum += ele;
            }

            Map.Entry<Integer, Integer> leftEntry = left.lastEntry();
            Map.Entry<Integer, Integer> rightEntry = right.firstEntry();

            if (leftEntry != null && rightEntry != null) {
                int leftMax = leftEntry.getKey();
                int rightMin = rightEntry.getKey();

                if (leftMax > rightMin || leftCount < rightCount) { // CHANGED: was left.size() < right.size()
                    if (leftEntry.getValue() == 1) left.remove(leftMax);
                    else left.put(leftMax, leftEntry.getValue() - 1);
                    leftSum -= leftMax;

                    if (rightEntry.getValue() == 1) right.remove(rightMin);
                    else right.put(rightMin, rightEntry.getValue() - 1);
                    rightSum -= rightMin;

                    left.put(rightMin, left.getOrDefault(rightMin, 0) + 1);
                    leftSum += rightMin;

                    right.put(leftMax, right.getOrDefault(leftMax, 0) + 1);
                    rightSum += leftMax;
                }
            }

            if (j - i + 1 == k) {
                int median = left.lastKey();                  // CHANGED: lower median is enough
                ans[i] = ((long) median * leftCount - leftSum)        // CHANGED: long math
                       + (rightSum - (long) median * rightCount);

                if (left.containsKey(nums[i])) {
                    int freq = left.get(nums[i]);
                    if (freq == 1) left.remove(nums[i]);
                    else left.put(nums[i], freq - 1);
                    leftCount--;
                    leftSum -= nums[i];
                } else {
                    int freq = right.get(nums[i]);
                    if (freq == 1) right.remove(nums[i]);
                    else right.put(nums[i], freq - 1);
                    rightCount--;
                    rightSum -= nums[i];
                }
                i++;
            }
        }
        return ans;
    }

    // min cost to pick k non-overlapping windows, considering window starts i, i+1, ...
    public long rec(int i, int k, int x, long[] cost, long[][] memo) {
        if (k == 0) return 0;
        if (i >= cost.length) return INF;
        if (memo[i][k] != -1) return memo[i][k];

        long skip = rec(i + 1, k, x, cost, memo);             // don't start a window at i
        long pick = rec(i + x, k - 1, x, cost, memo);         // start one at i, jump past it
        if (pick != INF) pick += cost[i];

        return memo[i][k] = Math.min(skip, pick);
    }

    public long minOperations(int[] nums, int x, int k) {
        long[] cost = costSlidingWindow(nums, x);
        long[][] memo = new long[cost.length][k + 1];
        for (long[] row : memo) Arrays.fill(row, -1);

        // warm the memo from the right so rec never goes more than one level deep
        for (int i = cost.length - 1; i >= 0; i--)
            for (int c = 1; c <= k; c++)
                rec(i, c, x, cost, memo);

        return rec(0, k, x, cost, memo);
    }
}