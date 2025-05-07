class Solution {
    int res = 1;
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] arr = new int[m][n];

        for(int i=0;i<m;i++){
            Arrays.fill(arr[i], 0);
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(arr[i][j]==0){
                    arr[i][j] = fnc(matrix,arr,i,j);
                }
            }
        }
        return res;
    }

    int fnc(int[][] matrix, int[][] arr, int row, int col){
        if(arr[row][col]!=0){
            return arr[row][col];
        }
        
        arr[row][col] = 1;

        int[][] moves ={{-1,0},{0,1},{1,0},{0,-1}};
        
        for(int i=0;i<moves.length;i++){
            int nrow = row+moves[i][0];
            int ncol = col+moves[i][1];
            if(nrow>=0 && nrow<matrix.length && ncol>=0 && ncol<matrix[0].length && matrix[row][col]<matrix[nrow][ncol]){
                arr[row][col] = Math.max(arr[row][col],1 + fnc(matrix,arr,nrow,ncol));
            }
        }
        res= Math.max(res, arr[row][col]);
        return arr[row][col];
    }
}