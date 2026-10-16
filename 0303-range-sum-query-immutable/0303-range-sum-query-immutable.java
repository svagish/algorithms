class NumArray {
    int n;
    int[] nums;
    int[] segTree;

    public NumArray(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        this.segTree = new int[4*n];
        this.buildSegTree(0, 0, n-1); 
    }

    public int buildSegTree(int i, int si, int ei) {
        if (si == ei) return this.segTree[i] = this.nums[si];
        int mid = si + (ei-si)/2;
        int left = buildSegTree(2*i+1, si, mid);
        int right = buildSegTree(2*i+2, mid+1, ei);
        return this.segTree[i] = left + right;
    } 

    public int query(int i, int ql, int qr, int l, int r) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return this.segTree[i];
        int mid = l + (r-l)/2;
        int leftSum = query(2*i+1, ql, qr, l, mid);
        int rightSum = query(2*i+2, ql, qr, mid+1, r);
        return leftSum + rightSum;
    }
    
    public int sumRange(int left, int right) {
        return query(0, left, right, 0, n-1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */