class Solution {
    public int sortPermutation(int[] nums) {
        int mask = -1; // all bits set, acts as identity for AND
        boolean sorted = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                sorted = false;
                mask &= i;
            }
        }
        return sorted ? 0 : mask; // mask = 0 if already sorted trivially, but sorted check handles that
    }
}