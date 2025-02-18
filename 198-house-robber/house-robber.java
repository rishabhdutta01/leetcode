class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        if(nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        dp[0] = nums[0];
        dp[1] = nums[1];

        int res = Math.max(nums[0], nums[1]);

        for(int i = 2; i<nums.length; i++) {
            for(int j = 0; j<i-1;j++) {
                dp[i] = Math.max(dp[i], nums[i] + dp[j]);    
            }
            res = Math.max(res, dp[i]);
        }

        return res;        
    }
}