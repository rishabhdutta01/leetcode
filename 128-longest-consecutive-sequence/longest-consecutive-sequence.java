class Solution {
    public int longestConsecutive(int[] nums) {
        Set set = new HashSet<>();
        int res= 0;
        int seq=0;

        for (int i=0;i<nums.length;i++) {
            set.add(nums[i]);
        }

        for (int i=0;i<nums.length;i++) {
            if(!set.contains(nums[i]-1)) {
                seq = 1;
                while(set.contains(nums[i]+seq)) {
                    seq++;
                }  
            res = Math.max(res, seq);
            }            
        }
        return res;
    }
}