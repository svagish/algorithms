class Solution {
    public int gcd(int a, int b) { 
        return a == 0 ? b : gcd(b%a, a);
    }

    public int findGCD(int[] nums) {
        int a = Integer.MAX_VALUE;
        int b = -1;
        for (int ele : nums) {
            a = Math.min(a, ele);
            b = Math.max(b, ele);
        }
        return gcd(a, b);
    }
}