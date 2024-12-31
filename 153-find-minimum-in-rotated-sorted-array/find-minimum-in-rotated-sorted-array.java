class Solution {
    public int findMin(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }

        int l=0;
        int r = nums.length - 1;
        int res = Integer.MAX_VALUE;
        
        while(l<=r) {
            int m = l+(r-l)/2;
            if(nums[m] < nums[l]) {
                r=m-1;
            }
            else{
                if(nums[m] > nums[r]) {
                    l=m+1;
                }
                else{
                    r=m-1;
                }
            }
            res= Math.min(res,nums[m]);
        }
        return res;
        
    }
}