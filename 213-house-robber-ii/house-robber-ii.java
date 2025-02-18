class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        
        return Math.max(find(nums,0, n-1), find(nums,1, n));
        
    }

    public int find(int[] nums, int i, int n){
        int prev2 = 0;
        int prev = nums[i];
        for(i=i+1; i<n; i++) {
            int take = prev2 + nums[i];
            int notTake = prev;
            int curr = Math.max(take,notTake);

            prev2 = prev;
            prev = curr;
        }

        return prev; 
    }
}