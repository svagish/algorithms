class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int incCurr = 1;
        int decCurr = 1;
        int incMax = 1;
        int decMax = 1;

        for (int i = 1; i < nums.length; i++) {            
            if (nums[i] > nums[i-1]) incMax = Math.max(++incCurr, incMax);
            else incCurr = 1;

            if (nums[i] < nums[i-1]) decMax = Math.max(++decCurr, decMax);
            else decCurr = 1;
        }

        return Math.max(incMax, decMax);
    }
}