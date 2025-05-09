//DP
class Solution {
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[height.length]; // idx of the first bar on left that is lower than current
        int[] lessFromRight = new int[height.length]; // idx of the first bar on right that is lower than current
        lessFromRight[height.length - 1] = height.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < height.length; i++) {
            int p = i - 1;
            while (p >= 0 && height[p] >= height[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = height.length - 2; i >= 0; i--) {
            int p = i + 1;
            while (p < height.length && height[p] >= height[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }
}

//Monotonic stack
// class Solution {
//     public int largestRectangleArea(int[] heights) {
//         int n = heights.length;
//         int res = Integer.MIN_VALUE;
//         Stack<Integer> s= new Stack<>();
//         s.push(-1);
//         for(int i=0;i<n;i++) {
//             while(s.peek()!=-1 && heights[i]<heights[s.peek()]){
//                 int h = heights[s.pop()];
//                 int w = i-s.peek()-1;
//                 res = Math.max(res,h*w);
//             }
//             s.push(i);
//         }

//         while(s.peek() != -1){
//             int h = heights[s.pop()];
//             int w = n-s.peek()-1;
//             res = Math.max(res,h*w);
//         }
//         return res;
//     }
// }
