class Solution {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        int i,j;
        int res = Integer.MAX_VALUE;
        for(i=0;i<m;i++){
            for(j=0;j<n;j++){
                if(i == 0){
                    dp[i][j] = grid[i][j];
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for(i=1;i<m;i++){
            for(j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    dp[i][j] = Math.min(dp[i][j], grid[i][j] + dp[i-1][k] + moveCost[grid[i-1][k]][j]);
                }
                if(i==m-1){
                    res=Math.min(res,dp[i][j]);
                }         
            }         
        }
        return res;
    }
}