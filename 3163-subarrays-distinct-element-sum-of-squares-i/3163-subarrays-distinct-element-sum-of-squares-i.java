class Solution {
    public int sumCounts(List<Integer> nums) {
        int s = 0;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int count = 0;
                Set<Integer> set = new HashSet<>();
                for (int k = i; k <= j; k++) {
                    set.add(nums.get(k));
                }
                s += set.size()*set.size();
            }
        }
        return s;
    }
}