class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }

        if(nums.length == 1) {
            return true;
        }
        
        int lasti = nums.length - 1;
        for(int i=lasti-1;i>=0;i--) {
            if(i+nums[i] >= lasti) {
                lasti=i;
            }
        }
        return lasti==0;
    }
}