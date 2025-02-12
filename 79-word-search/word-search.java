class Solution {
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0 || word == null || word.length()==0) {
            return false;
        }

        for (int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length;j++) {
                if (board[i][j] == word.charAt(0)){
                    if(go(board, word, i, j, 0)){
                        return true;
                    }
                }
            }
        }
        return false;  
    }

    boolean go(char[][] board, String word, int i, int j, int k) {
        if(k == word.length()) {
            return true;
        }
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j] != word.charAt(k)){
            return false;
        }
        boolean res = false;
        if(board[i][j] == word.charAt(k)){
            char temp = board[i][j];
            board[i][j] = '#';
            res = go(board, word, i-1, j, k+1) 
                || go(board, word, i+1, j, k+1)
                || go(board, word, i, j-1, k+1)
                || go(board, word, i, j+1, k+1);
            board[i][j] = temp;
        }
        return res;
    }
}