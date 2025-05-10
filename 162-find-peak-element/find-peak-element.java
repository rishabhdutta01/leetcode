public class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            //decreasing seq thus search on left
            if (nums[mid] > nums[mid + 1]) r = mid;
            //increasing seq thus search on right
            else l = mid + 1;
        }
        return l;
    }
}