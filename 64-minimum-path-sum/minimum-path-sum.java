class Solution {
    public int minPathSum(int[][] grid) {
        int i,j;

        for(j=1;j<grid[0].length;j++){
            grid[0][j] = grid[0][j] + grid[0][j-1];
        }
        for(i=1;i<grid.length;i++){
            grid[i][0] = grid[i][0] + grid[i-1][0];
        }
        for(i=1;i<grid.length;i++){
            for(j=1;j<grid[0].length;j++){
                grid[i][j] = grid[i][j] + Math.min(grid[i-1][j],grid[i][j-1]);
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
}