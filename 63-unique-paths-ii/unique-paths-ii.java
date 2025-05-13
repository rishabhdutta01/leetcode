class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int i=0;
        int j=0;
        for(i=0;i<m;i++){
            for(j=0;j<n;j++){
                if(obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = 1;
                }
            }
        }

        boolean cantgo = false;
        for(j=0;j<n;j++){
            if(obstacleGrid[0][j] == 0){
                cantgo = true;
                continue;
            }
            if(cantgo)
                obstacleGrid[0][j] = 0;
        }

        cantgo = false;
        for(i=0;i<m;i++){
            if(obstacleGrid[i][0] == 0){
                cantgo = true;
                continue;
            }
            if(cantgo)
                obstacleGrid[i][0] = 0;
        }
        

        for(i=1;i<m;i++){
            for(j=1;j<n;j++){
                if(obstacleGrid[i][j] == 0){
                    continue;
                }
                // if(obstacleGrid[i-1][j] == 0 || obstacleGrid[i][j-1] == 0){
                //     obstacleGrid[i][j] = Math.max(obstacleGrid[i-1][j], obstacleGrid[i][j-1]);
                // } else {
                    obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                // }                
            }
        }
        return obstacleGrid[m-1][n-1];
    }
}