class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums.length == 1){
            return nums[0] == k ? 1 : 0;
        }

        int cnt = 0;
        int n = nums.length;
        int sum = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);

        for(int i=0;i<n;i++){
            sum = sum + nums[i];
            if(map.containsKey(sum-k))
                cnt += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum,0)+1);
        }
        return cnt;
    }
}