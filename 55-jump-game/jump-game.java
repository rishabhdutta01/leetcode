class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if(nums.length == 1) {
            return true;
        }

        int l = nums.length;
        boolean[] dp = new boolean[l];
        Arrays.fill(dp,false);
        dp[0] = true;
        int i = 0;
        while(i<l && dp[i] == true) {
            if(nums[i] >= l-i-1) {
                return true;
            }
            int j=nums[i];
            while(j>0){
                dp[j+i] = true;
                j--;
            }
            i++;
        }
        return dp[l-1];    
    }
}