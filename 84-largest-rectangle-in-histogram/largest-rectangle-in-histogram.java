class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int res = Integer.MIN_VALUE;
        Stack<Integer> s= new Stack<>();
        s.push(-1);
        int idx = 0;
        while(idx < n){
            while(s.peek()!=-1 && heights[idx]<heights[s.peek()]){
                int h = heights[s.pop()];
                int w = idx-s.peek()-1;
                res = Math.max(res,h*w);
            }
            s.push(idx++);
        }

        while(s.peek() != -1){
            int h = heights[s.pop()];
                int w = idx-s.peek()-1;
                res = Math.max(res,h*w);
        }

        return res;
    }
}

// class Solution {
//     public int largestRectangleArea(int[] heights) {
//         int n = heights.length;
//         int[][] min = new int[n][n];
//         for(int j=0;j<n;j++){
//             min[j][j] = heights[j];
//             for(int i=j-1;i>=0;i--){
//                 min[i][j] = Math.min(min[i+1][j], min[i][j-1]);
//             }
//         }
//         int res= Integer.MIN_VALUE;
//         for(int j=0;j<n;j++){
//             for(int i=j-1;i>=0;i--){
//                 min[i][j] = Math.max(min[i][j]*(j-i+1),Math.max(min[i+1][j], min[i][j-1]));
//             }
//         }
//         return min[0][n-1];
//     }
// }