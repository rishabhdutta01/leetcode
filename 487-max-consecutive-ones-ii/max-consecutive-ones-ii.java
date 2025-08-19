class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int l=0;
        int k=1;
        int used = 0;
        int res = 0;

        for(int r=0;r<nums.length;r++){
            if(nums[r]==0){
                while(used == k){
                    if(nums[l] == 0) used--;
                    l++;
                }   
                used++;           
            }

            res = Math.max(res,r-l+1);
        }
        return res;
    }
}