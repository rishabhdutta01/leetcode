class Solution {
    int[] sums;
    int[] w;
    int n;
    public Solution(int[] w) {
        n = w.length;
        sums = new int[n];
        sums[0] = w[0];
        for(int i=1;i<n;i++){
            sums[i]=sums[i-1] + w[i];
        }
        this.w = w;
    }
    
    public int pickIndex() {
        Random random = new Random();
        int num = random.nextInt(sums[n-1])+1;
        int l=0;
        int r=n-1;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(sums[mid] == num) return mid;
            else if(sums[mid]>num) r = mid-1;
            else l = mid+1;
        }
        return l;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */