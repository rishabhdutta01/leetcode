class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        // 2 passes because we need to have the 2 arrays fully pass eachother 
        return Math.max(helper(nums1,nums2),helper(nums2,nums1));
    }
    
    private int helper(int[] arr1, int[] arr2){
        int max = 0;
        // move arr1 forward one step at a time
        for(int i = 1; i <= arr1.length; i++){
            // set the comparison starting points of both arr1 and arr2
            int arr1Index = arr1.length - i;
            int arr2Index = 0;
            int matched = 0;
            while(arr1Index < arr1.length && arr2Index < arr2.length){
                // increment match count each time a match is found; reset otherwise
                matched = arr1[arr1Index++] == arr2[arr2Index++]? matched + 1: 0;
                max = Math.max(max,matched);
            }
        }
        return max;
    }
}