class MedianFinder {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder() {
        this.left = new PriorityQueue<>(Collections.reverseOrder());
        this.right = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if (this.left.size() <= this.right.size()) {
            this.left.offer(num);
        }
        else this.right.offer(num);

        // balance, swap leftMax to rightMin from left to right
        if (!this.left.isEmpty() && !this.right.isEmpty() && this.left.peek() > this.right.peek()) {
            int leftMax = this.left.poll();
            int rightMin = this.right.poll();

            this.left.offer(rightMin);
            this.right.offer(leftMax);
        }
    }
    
    public double findMedian() {
        // System.out.println("left: " + this.left);
        // System.out.println("right: " + this.right);
        int leftMax = this.left.peek();
        if (((this.left.size() + this.right.size()) & 1)  == 0) {
            int rightMin = this.right.peek();
            return ((double)leftMax + (double)rightMin)/2.0;
        }
        return (double) leftMax;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */