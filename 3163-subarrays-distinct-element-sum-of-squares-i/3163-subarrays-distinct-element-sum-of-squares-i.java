class Solution {
    public int sumCounts(List<Integer> nums) {
        int s = 0;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(nums.get(j));
                s += set.size()*set.size();
            }
        }
        return s;
    }
}