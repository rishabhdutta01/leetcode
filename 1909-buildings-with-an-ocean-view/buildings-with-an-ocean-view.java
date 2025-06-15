class Solution {
    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        int max = heights[n-1];
        List<Integer> l = new ArrayList<>();
        l.add(n-1);

        for(int i=n-2;i>=0;i--){
            if(heights[i] <= max) continue;
            else{
                l.add(i);
                max = heights[i];
            }
        }

        int[] res = new int[l.size()];
        int j=0;
        for(int i=l.size()-1;i>=0;i--){
            res[j++] = l.get(i);
        }
        return res;
    }
}