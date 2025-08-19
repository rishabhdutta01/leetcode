class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        List<Integer> arr = new ArrayList<>();
        arr.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            if(nums[i] >= arr.getLast()){
                if(nums[i] > arr.getLast()) arr.add(nums[i]);
            } else{
                int l = 0;
                int r = arr.size()-1;
                int ans = arr.size();
                while(l<=r){
                    int mid = l+(r-l)/2;
                    if(arr.get(mid) >= nums[i]){
                        ans = mid;
                        r = mid-1;
                    } else l=mid+1;
                }
                arr.set(ans, nums[i]);
            }
        }
        return arr.size();
    }
}