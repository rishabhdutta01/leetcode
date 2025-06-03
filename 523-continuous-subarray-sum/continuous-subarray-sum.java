class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sums = new int[n];
        sums[0] = nums[0];
        for(int i=1;i<n;i++){
            sums[i] = sums[i-1] + nums[i];
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for(int i=0;i<n;i++){
            int rem = sums[i]%k;
            if(map.containsKey(rem)){
                if(i-map.get(rem)>=2){
                    return true;
                }
            } else map.put(rem, i);
        }
        return false;
    }
}