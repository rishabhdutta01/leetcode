class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        
        int prev2 = 0;
        int prev = nums[0];

        for(int i = 1; i<nums.length; i++) {
            int take = prev2 + nums[i];
            int notTake = prev;
            int curr = Math.max(take,notTake);

            prev2 = prev;
            prev = curr;
        }

        return prev;        
    }
}