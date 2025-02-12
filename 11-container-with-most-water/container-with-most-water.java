class Solution {
    public int maxArea(int[] height) {
        if(height == null || height.length == 1) {
            return 0;
        }

        int res = 0;
        int l = 0;
        int r = height.length - 1;

        while(l<r) {
            res = Math.max(res, Math.min(height[l],height[r])*(r-l));
            if(height[l]>height[r])
                r--;
            else
                l++;
        }
        return res;
    }
}