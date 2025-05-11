class SparseVector {
    
    Map<Integer, Integer> m;
    SparseVector(int[] nums) {
        m = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                m.put(i,nums[i]);
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int sum = 0;
        for(Integer i: m.keySet()){
            sum += this.get(i)*vec.get(i);
        }
        return sum;
    }

    public int get(Integer idx){
        if(m.containsKey(idx)){
            return m.get(idx);
        } else{
            return 0;
        }
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);