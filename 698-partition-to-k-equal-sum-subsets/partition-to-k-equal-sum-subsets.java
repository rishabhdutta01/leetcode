class Solution {

    public boolean canPartitionKSubsets(int[] arr, int k) {
        int totalArraySum = 0;
        int n = arr.length;
        
        for (int i = 0; i < n; ++i) {
             totalArraySum += arr[i];
        }
      
        // If total sum not divisible by k, we can't make subsets.
        if (totalArraySum % k != 0) { 
            return false;
        }
      
        // Sort in decreasing order.
        Arrays.sort(arr);
        reverse(arr);

        char[] visited = new char[arr.length];
        Arrays.fill(visited, '0');

        Map<String, Boolean> memo = new HashMap<>();
        return backtrack(arr,k,totalArraySum/k,0,0,visited,memo);  
    }

    void reverse(int[] arr){
        int n = arr.length;
        for(int i=0;i<n/2;i++){
            int temp = arr[i];
            arr[i] = arr[n-1-i];
            arr[n-1-i] = temp;
        }
    }

    boolean backtrack(int[] arr, int k, int sum, int curr, int i, char[] visited, Map<String, Boolean> memo){
        if(curr > sum){
            return false;
        }
        if(k==1){
            return true;
        }
        if(curr == sum){
            boolean res = backtrack(arr,k-1,sum,0,0,visited,memo);
            memo.put(new String(visited), res);
            return res;
        }
        if(i==arr.length){
            return false;
        }
        if(memo.containsKey(new String(visited))){
            return memo.get(new String(visited));
        }

        boolean yes = false;
        if(visited[i] =='0'){
            visited[i] = '1';
            yes= backtrack(arr,k,sum,curr+arr[i],i+1,visited,memo);
            visited[i] = '0';
        }
        
        boolean no = backtrack(arr,k,sum,curr,i+1,visited,memo);
        boolean res = no||yes;
        memo.put(new String(visited), res);
        return res;

    }
}





// class Solution {
//     private boolean backtrack(int[] arr, int index, int count, int currSum, int k, 
//                               int targetSum, char[] taken, HashMap<String, Boolean> memo) {

//         int n = arr.length;
      
//         // We made k - 1 subsets with target sum and last subset will also have target sum.
//         if (count == k - 1) { 
//             return true;
//         }
        
//         // No need to proceed further.
//         if (currSum > targetSum) { 
//             return false;
//         }
        
//         String takenStr = new String(taken);
        
//         // If we have already computed the current combination.
//         if (memo.containsKey(takenStr)) {
//             return memo.get(takenStr);
//         }
      
//         // When curr sum reaches target then one subset is made.
//         // Increment count and reset current sum.
//         if (currSum == targetSum) {
//             boolean ans = backtrack(arr, 0, count + 1, 0, k, targetSum, taken, memo);
//             memo.put(takenStr, ans);
//             return ans;
//         }
        
//         // Try not picked elements to make some combinations.
//         for (int j = index; j < n; ++j) {
//             if (taken[j] == '0') {
//                 // Include this element in current subset.
//                 taken[j] = '1';
                
//                 // If using current jth element in this subset leads to make all valid subsets.
//                 if (backtrack(arr, j + 1, count, currSum + arr[j], k, targetSum, taken, memo)) {
//                     return true;
//                 }
                
//                 // Backtrack step.
//                 taken[j] = '0';
//             }
//         } 
      
//         // We were not able to make a valid combination after picking each element from array,
//         // hence we can't make k subsets.
//         memo.put(takenStr, false);
//         return false;
//     }
    
//     void reverse(int[] arr) {
//         for (int i = 0, j = arr.length - 1; i < j; i++, j--) { 
//             int temp = arr[i];
//             arr[i] = arr[j];
//             arr[j] = temp;
//         }
//     }
  
//     public boolean canPartitionKSubsets(int[] arr, int k) {
//         int totalArraySum = 0;
//         int n = arr.length;
        
//         for (int i = 0; i < n; ++i) {
//              totalArraySum += arr[i];
//         }
      
//         // If total sum not divisible by k, we can't make subsets.
//         if (totalArraySum % k != 0) { 
//             return false;
//         }
      
//         // Sort in decreasing order.
//         Arrays.sort(arr);
//         reverse(arr);
        
//         int targetSum = totalArraySum / k;
        
//         char[] taken = new char[n];
//         for(int i = 0; i < n; ++i) {
//             taken[i] = '0';
//         }
        
//         // Memoize the ans using taken element's string as key.
//         HashMap<String, Boolean> memo = new HashMap<>();
      
//         return backtrack(arr, 0, 0, 0, k, targetSum, taken, memo);
//     }
// }