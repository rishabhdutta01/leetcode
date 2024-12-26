class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        for(int i =0; i<nums.length;i++) {
            if (sumMap.get(target - nums[i]) != null) {
                return new int[]{sumMap.get(target - nums[i]),i};
            }
            sumMap.put(nums[i],i);
        }
        return null;
    }
}