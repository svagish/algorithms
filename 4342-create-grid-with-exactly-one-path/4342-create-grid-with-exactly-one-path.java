class Solution {
    public String[] createGrid(int m, int n) {
        char[][] arr = new char[m][n];
        String[] ans = new String[m];
        int ptr = 0;

        for (char[] a : arr) Arrays.fill(a, '.');
        for (int i = 0; i < m-1; i++) {
            for (int j = 1; j < n; j++) {
                arr[i][j] = '#';
            }
        }

        for (int i = 0; i < m; i++) {
            StringBuilder sb = new StringBuilder(n);
            for (int j = 0; j < n; j++) {
                sb.append(arr[i][j]);
            }
            ans[ptr++] = sb.toString();
        }

        return ans;
    }
}