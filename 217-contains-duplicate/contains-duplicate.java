class Solution {
    public boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length == 0){
            return false;
        }
        if(nums.length == 1){
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);

        for(int i = 1; i<nums.length; i++) {
            if(map.get(nums[i])!= null) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}