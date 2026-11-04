class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();
        
        int leftCount = 0;
        int rightCount = 0;

        int i = 0;
        int n = nums.length;
        double[] ans = new double[n-k+1];
        
        for (int j = 0; j < n; j++) {
            int ele = nums[j];

            if (left.isEmpty() || leftCount <= rightCount) {
                left.put(ele, left.getOrDefault(ele, 0) + 1);
                leftCount++;
            }
            else {
                right.put(ele, right.getOrDefault(ele, 0) + 1);
                rightCount++;
            }

            Map.Entry<Integer, Integer> leftEntry = left.lastEntry();
            Map.Entry<Integer, Integer> rightEntry = right.firstEntry(); 
            
            // balancing
            if (leftEntry != null && rightEntry != null) {
                int leftMax = leftEntry.getKey();
                int rightMin = rightEntry.getKey();

                // left max > right min, swap
                if (leftMax > rightMin || left.size() < right.size()) {
                    // remove from left
                    if (leftEntry.getValue() == 1) left.remove(leftMax);
                    else left.put(leftMax, leftEntry.getValue()-1);
                    // remove from right
                    if (rightEntry.getValue() == 1) right.remove(rightMin);
                    else right.put(rightMin, rightEntry.getValue()-1);
                    // add in left
                    left.put(rightMin, left.getOrDefault(rightMin, 0) + 1); 
                    // add in right
                    right.put(leftMax, right.getOrDefault(leftMax, 0) + 1);
                }

            }

            // reduce window size
            if (j-i+1 == k) {
                // System.out.println(i);
                // System.out.println("left: "+left);
                // System.out.println("right:" +right);
                // System.out.println();

                double median = (((k & 1) == 0) ? (double)left.lastKey()/2.0 + (double) (right.firstKey())/2.0 : left.lastKey());
                ans[i] = median;

                if(left.containsKey(nums[i])) {
                    int freq = left.get(nums[i]);
                    if (freq == 1) left.remove(nums[i]);
                    else left.put(nums[i], freq-1); 
                    leftCount--;
                }
                else {
                    int freq = right.get(nums[i]);
                    if (freq == 1) right.remove(nums[i]);
                    else right.put(nums[i], freq-1);
                    rightCount--;
                }
                i++;
            }
        }

        return ans;
    }
}