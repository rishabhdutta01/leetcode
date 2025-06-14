class Solution {
    private void reverse(int[] nums, int left, int right)
    {
        int temp;
        while(left<right)
        {
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            right--;
            left++;
        }
    }
    public void nextPermutation(int[] nums) {
        int n = nums.length, i, ind=-1, temp;
        for(i=n-2;i>=0;i--)
        {
            if(nums[i]<nums[i+1])
            {
                ind = i;
                break;
            }
        }
        if(ind == -1)
        {
            reverse(nums,0,n-1);
            return;
        }
        for(i=n-1;i>ind;i--)
        {
            if(nums[i]>nums[ind])
            {
                temp = nums[i];
                nums[i] = nums[ind];
                nums[ind] = temp;
                break;
            }
        }
        reverse(nums, ind+1, n-1);
    }
}