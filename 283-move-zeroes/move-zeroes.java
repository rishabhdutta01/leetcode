class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int l = 0;
        for(l=0;l<n;l++){
            if(nums[l] == 0)break;
        }
        if(l==n) return;

        int r = l+1;
        
        while(r<n){
            if(nums[r] == 0) {
                r++;
                continue;
            }

            nums[l] = nums[r];
            nums[r] = 0;
            l++;
            r++;            
        }
    }
}