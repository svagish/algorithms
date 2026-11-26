class Solution {
    public boolean canAliceWin(int n) {
        for (int i = 10; i >= 0; i--) {
            if (i > n) return i % 2 != 0;
            n -= i;
        }
        return true;
    }
}