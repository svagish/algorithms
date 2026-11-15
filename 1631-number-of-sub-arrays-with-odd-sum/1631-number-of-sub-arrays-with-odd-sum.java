class Solution {
    public int numOfSubarrays(int[] arr) {
        int evenCount = 1;
        int oddCount = 0;
        int ssf = 0;
        int ans = 0;
        int MOD = (int)1e9 + 7;
        // odd - even = odd
        // even - odd = odd
        for (int ele : arr) {
            ssf += ele;
            if ((ssf & 1) != 0) {
                ans = (ans + evenCount)%MOD;
                oddCount++;
            }
            else {
                ans = (ans + oddCount)%MOD;
                evenCount++;
            }
        }
        return ans;
    }
}