class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) {
            if( i == 0 || nums[i] != nums[i-1]) {
                twoSum(i, nums,result);
            }
        }
        return result;
    }

    private void twoSum(int i, int nums[], List<List<Integer>> result) {
         int low = i+1;
         int high = nums.length - 1;
         int compl = -nums[i];

         while(low < high) {
             int sum = nums[low] + nums[high];
             if(sum > compl) {
                 high--;
                 continue;
             }
             if(sum < compl) {
                 low++;
                 continue;
             }
             result.add(Arrays.asList(nums[i], nums[low++], nums[high--]));
             while(low < high && nums[low] == nums[low-1]){
                low++;
             }
         }   
         
    }
}