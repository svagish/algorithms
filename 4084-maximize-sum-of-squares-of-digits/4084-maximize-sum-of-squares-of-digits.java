class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9*num) return "";
        StringBuilder sb = new StringBuilder();
        
        while (num > 0) {
            boolean didPick = false;
            for (int d = 9; d >= 0; d--) {
                // can pick
                if (d <= sum) {
                    sum -= d;
                    sb.append(d);
                    didPick = true;
                    break;
                }
            }
            if (!didPick) return "";
            num--;
        }

        return sb.toString();
    }
}