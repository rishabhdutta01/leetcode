class Solution {
    public int climbStairs(int n) {
        int[] l = new int[n+1];
        l[0] = 1;
        l[1] = 1;
        for(int i=2;i<n+1;i++){
            l[i] = l[i-1] + l[i-2];
        }
        return l[n];
    }
}