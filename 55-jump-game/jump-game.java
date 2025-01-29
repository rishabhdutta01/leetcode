class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if(nums.length == 1) {
            return true;
        }

        int l = nums.length;
        int j = nums[0];
        int i = 0;

        if(j==0){
            return false;
        }
        j--;
        i++;
        while(j>=0 && i<l) {
            if(nums[i] > j) {
                j = nums[i];
            }
            j--;
            i++;
        }
        if(j<0){
            if(i==l)
                return true;
            return false;
        }  
        return true;
    }
}