class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return new int[] {-1,-1};
        }

        int l=0;
        int r = nums.length-1;

        int mid = 0;
        boolean found = false;
        while(l<=r){
            mid = l+(r-l)/2;
            if(nums[mid] == target){
                found = true;
                break;
            }else if(nums[mid] > target) r = mid - 1;
            else l = mid+1;
        }

        if(!found) return new int[] {-1,-1};

        return new int[]{lowest(l,mid-1, target, nums), highest(mid+1,r, target, nums)};
    }

    int lowest(int l, int r, int target, int[] nums){
        int ans = r+1;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(nums[mid] >= target) {
                ans = mid;
                r = mid - 1;
            }
            else l = mid+1;
        }
        return ans;
    }

    int highest(int l, int r, int target, int[] nums){
        int ans = l-1;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(nums[mid] <= target) {
                ans = mid;
                l = mid + 1;
            }
            else r = mid-1;
        }
        return ans;
    }
}