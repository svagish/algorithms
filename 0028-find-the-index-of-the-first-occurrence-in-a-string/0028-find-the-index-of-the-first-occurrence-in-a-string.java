class Solution {
    public int strStr(String text, String pat) {
        int patLen = pat.length();  
        int textLen = text.length();
        if (patLen > textLen) return -1;

        long p = 31;
        long pow = 31;
        long m = (long)1e9 + 7;
        long[] pa = new long[textLen];
        pa[0] = 1;

        // pre-calc powers
        for (int i = 1; i < pa.length; i++) {
            pa[i] = pow;
            pow = (pow * p) % m; 
        }

        // calc pat hash
        long pHash = 0;
        for (int i = 0; i < patLen; i++) {
            pHash = (pHash + (pat.charAt(i)-'a'+1) * pa[i]) % m;
        }

        // slide window of size patLen over text
        long tHash = 0;
        int i = 0;
        for (int j = 0; j < textLen; j++) {
            tHash = (tHash + (text.charAt(j)-'a'+1) * pa[j]) % m;
            if (j-i+1 == patLen) {
                if (tHash == (pHash*pa[i])%m) return i;
                tHash = (tHash - ((text.charAt(i)-'a'+1) * pa[i]) % m + m) % m;
                i++;
            }
        }

        return -1;
    }
}