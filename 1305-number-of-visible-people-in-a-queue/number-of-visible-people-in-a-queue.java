class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        List<Integer> arr = new ArrayList<>();
        int n = heights.length;
        int[] res = new int[n];
        res[n-1] = 0;
        arr.add(heights[n-1]);
        for(int i=n-2;i>=0;i--){
            int l = arr.size();
            int j = l-1;
            while(j>=0){
                if(heights[i] < arr.get(j)){
                    res[i]++;
                    break;
                } else{
                    res[i]++;
                    arr.removeLast();
                    j--;
                }
            }
            arr.add(heights[i]);
        }
        return res;
    }
}