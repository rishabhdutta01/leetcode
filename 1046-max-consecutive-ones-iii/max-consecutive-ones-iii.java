class Solution {
    public int longestOnes(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        if(nums.length == k){
            return nums.length;
        }

        int n = nums.length;
        int l = 0;
        int r = 0;      
        int res=0;

        while(r<n){
            if(nums[r] == 1){
                if(k>=0){
                    res++;
                } else{
                    if(nums[l] == 0)
                        k++;
                    l++;
                }
                r++;
            } else{
                k--;
                if(k>=0){
                    res++;
                } else {
                    if(nums[l] == 0)
                        k++;
                    l++;
                }
                r++;
            }
        }
        return res;
    }
}