class Solution {
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        
        List<Pair<Integer, Integer>> arr = new ArrayList<>();
        arr.add(new Pair<>(nums[0], 0));

        int max = 0;

        for(int i=1;i<n;i++){
            if(nums[i] < arr.getLast().getKey()){
                arr.add(new Pair<>(nums[i], i));
            }else{
                for(int j=0;j<arr.size();j++){
                    if(nums[i] >= arr.get(j).getKey() && max < i-arr.get(j).getValue()){
                        max = i-arr.get(j).getValue();
                        break;
                    }
                }
            }
        }
        return max;
    }
}

// class Solution {
//     public int maxWidthRamp(int[] nums) {
//         int n = nums.length;
//         int[] dp = new int[n];

//         int max = 0;
//         for(int i=1;i<n;i++){
//             dp[i] = dp[i-1];
//             for(int j=i-dp[i-1]-1;j>=0;j--){
//                 if(nums[j] <= nums[i] && dp[i] < i-j){
//                     dp[i] = i-j;
//                 }
//             }
//         }
//         return dp[n-1];
//     }
// }