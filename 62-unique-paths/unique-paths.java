class Solution {
    public int uniquePaths(int m, int n) {
        if(m==1 && n ==1) {
             return 1;
        }
        int[][] arr = new int[m][n];

        int i,j;

        for(j=0;j<n;j++){
            arr[0][j] = 1;
        }
        for(i=0;i<m;i++){
            arr[i][0] = 1;
        }

        for(i=1;i<m;i++){
            for(j=1;j<n;j++){
                arr[i][j] = arr[i-1][j] + arr[i][j-1];
            }
        }
        return arr[m-1][n-1];
    }
}