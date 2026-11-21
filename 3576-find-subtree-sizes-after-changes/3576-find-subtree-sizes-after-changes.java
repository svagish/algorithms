class Solution {
    List<Integer>[] tree;
    int[] newPar, size;
    int[] last = new int[26];   // closest ancestor index per letter, -1 if none
    String s;

    void dfs(int u) {
        int c = s.charAt(u) - 'a';
        int prev = last[c];
        if (prev != -1) newPar[u] = prev;   // reattach to closest same-letter ancestor
        last[c] = u;                        // always register, even if u moved

        for (int v : tree[u]) dfs(v);

        last[c] = prev;                     // backtrack, so no clone() is needed
        // every node that reattaches to u is an original descendant, so it is already done
        if (u != 0) size[newPar[u]] += size[u];
    }

    public int[] findSubtreeSizes(int[] parent, String s) {
        this.s = s;
        int n = s.length();
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) tree[parent[i]].add(i);

        newPar = parent.clone();
        size = new int[n];
        Arrays.fill(size, 1);
        Arrays.fill(last, -1);

        dfs(0);
        return size;
    }
}