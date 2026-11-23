class ProductOfNumbers {
    int[] prefix = new int[40_002];   
    int size = 1;                     

    public ProductOfNumbers() {
        prefix[0] = 1;
    }

    public void add(int num) {
        if (num == 0) {
            size = 1;                 
        } else {
            prefix[size] = prefix[size - 1] * num;
            size++;
        }
    }

    public int getProduct(int k) {
        if (k >= size) return 0;      
        return prefix[size - 1] / prefix[size - 1 - k];
    }
}