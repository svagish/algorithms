class Solution {
    public boolean checkString(String s) {
        boolean bFlag = false;
        for (char ch : s.toCharArray()) {
            if (ch == 'b') bFlag = true;
            if (bFlag && ch == 'a') return false;
        }
        return true;
    }
}