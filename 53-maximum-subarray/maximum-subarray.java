class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        else if(nums.length == 1){
            return nums[0];
        }

        int l = nums.length;
        int res[] = new int[l];
        res[0] = nums[0];
        int maxres = nums[0];
        
        for(int i=1; i < l;i++){
            res[i] = Math.max(nums[i], nums[i] + res[i-1]);
            maxres = Math.max(maxres, res[i]);
        }
        return maxres;
    }
}