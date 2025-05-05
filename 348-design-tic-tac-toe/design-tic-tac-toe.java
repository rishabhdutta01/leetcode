class TicTacToe {
    int[][] board;
    int[] top;
    int[] right;
    int n;

    public TicTacToe(int n) {
        this.n = n;
        board = new int[n][n];
        top = new int[n+1];
        right = new int[n+1];
    }
    
    public int move(int row, int col, int player) {
        int res = 0;
        if(player==1){
            board[row][col] = -1;

            top[col] -=1;
            if(top[col] == -n) res=1;

            right[row] -= 1;
            if(right[row] == -n) res=1;

            if(row==col){
                right[n] -=1;
                if(right[n]==-n) res=1;
            }

            if(row+col==n-1){
                top[n] -=1;
                if(top[n]==-n) res=1;
            }

            return res;
        } else{
            board[row][col] = 1;

            top[col] +=1;
            if(top[col] == n) res=2;

            right[row] += 1;
            if(right[row] == n) res=2;

            if(row==col){
                right[n] +=1;
                if(right[n]==n) res=2;
            }

            if(row+col==n-1){
                top[n] +=1;
                if(top[n]==n) res=2;
            }

            return res;
        }
        
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */