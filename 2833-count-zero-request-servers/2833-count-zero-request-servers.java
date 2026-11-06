class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = logs.length;
        int[] ans = new int[queries.length];
        
        // sort logs
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // sort queries
        int[][] queriesSorted = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) queriesSorted[i] = new int[]{queries[i], i};
        Arrays.sort(queriesSorted, (a, b) -> Integer.compare(a[0], b[0]));

        // sliding window
        int i = 0;
        int j = 0;
        Map<Integer, Integer> sv = new HashMap<>();
        for (int[] q : queriesSorted) {
            int s = q[0]-x;
            int e = q[0];

            // expand
            while (j < m && logs[j][1] <= e) {
                sv.put(logs[j][0], sv.getOrDefault(logs[j][0], 0) + 1);
                j++;
            }

            // shrink
            while (i < m && logs[i][1] < s) {
                sv.computeIfPresent(logs[i][0], (k,v)->(v == 1) ? null : v-1);
                i++;
            }

            ans[q[1]] = n-sv.size();
        }

        return ans;
    }
}