class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<nums.length && nums[i]<=0;i++){
            if(i==0 || nums[i-1] != nums[i]) 
                fnc(nums, i, res);
        }
        return res;
    }

    void fnc(int[] nums, int i, List<List<Integer>> res){
        Set<Integer> m = new HashSet<>();
        for(int j=i+1;j<nums.length;j++){
            int complement = -nums[i] -nums[j];
            if(m.contains(complement)){
                res.add(Arrays.asList(nums[i], nums[j], complement));
                while(j+1<nums.length && nums[j]==nums[j+1]){
                    j++;
                }
            } 
            m.add(nums[j]);           
        }
    }
}