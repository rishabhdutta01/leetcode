class Solution {
    public int distinctAverages(int[] nums) {
        int min = 102;
        int max = -1;
        for (int i = 0; i<nums.length; i++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        int[] arr = new int[max+1];

        for (int i = 0; i<nums.length; i++){
            arr[nums[i]]+=1;
        }

        Set<Double> s = new HashSet<>();

        while(min<=max){
            if(arr[min] == 0 && arr[max]==0){
                min++;
                max--;
            } else if(arr[min] == 0) {
                min++;
            } else if(arr[max] == 0) {
                max--;
            } else{
                s.add((((double)min+(double)max)/2));
                arr[min]--;
                arr[max]--;
            }
        }
        return s.size();
    }
}