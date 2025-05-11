class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        if(n==1){
            if(k==1) return nums[0];
            else return -1;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);
        }

        int[] arr = new int[max-min+1];

        for(int i=0;i<n;i++){
            arr[nums[i]-min] +=1;
        }

        for(int i=arr.length-1;i>=0;i--){
            if(arr[i] == 0) continue;
            k-=arr[i];
            if(k<=0){
                return i+min;
            }
        }
        return -1;
    }
}