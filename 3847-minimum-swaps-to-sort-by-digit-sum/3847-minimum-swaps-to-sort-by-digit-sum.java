class Node {
    int index;
    int sum;
    public Node(int index, int sum) {
        this.index = index;
        this.sum = sum;
    }
    @Override
    public String toString() {
        return "(" + index + ", " + sum + ")";
    }
}

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        Node[] arr = new Node[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int num = nums[i];
            while (num > 0) {
                sum += num%10;
                num /= 10;
            }
            arr[i] = new Node(i, sum);
        }     

        Arrays.sort(arr, (Node a, Node b)->{
            int cmp = Integer.compare(a.sum, b.sum);
            return cmp == 0 ? Integer.compare(nums[a.index], nums[b.index]) : cmp;
        });

        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] || arr[i].index == i) { visited[i] = true; continue; }
            int cycleLen = 0, j = i;
            while (!visited[j]) {
                visited[j] = true;
                j = arr[j].index;
                cycleLen++;
            }
            count += cycleLen - 1;
        }
        return count;
    }
}