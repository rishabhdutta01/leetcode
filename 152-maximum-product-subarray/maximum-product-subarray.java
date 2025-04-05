class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int n = nums.length;
        int[] lprod = new int[n];
        int[] rprod = new int[n];

        int res = Math.max(nums[0], nums[n - 1]);

        lprod[0] = nums[0] == 0 ? 1 : nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == 0) {
                lprod[i] = 1;
                res = Math.max(res, 0);
            } else {
                lprod[i] = nums[i] * lprod[i - 1];
                res = Math.max(res, lprod[i]);
            }

        }

        rprod[n - 1] = nums[n - 1] == 0 ? 1 : nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] == 0) {
                rprod[i] = 1;
                res = Math.max(res, 0);
            } else {
                rprod[i] = nums[i] * rprod[i + 1];
                res = Math.max(res, rprod[i]);
            }
        }

        return res;
    }
}