class Solution {
    public void solveSudoku(char[][] board) {
        Map<Integer, HashSet<Character>> rows = new HashMap<>();
        Map<Integer, HashSet<Character>> cols = new HashMap<>();
        Map<Integer, HashSet<Character>> box = new HashMap<>();

        int n = board.length;

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!rows.containsKey(i)){
                    rows.put(i, new HashSet<>());
                }

                if(!cols.containsKey(j)){
                    cols.put(j, new HashSet<>());
                }

                int boxId = (i/3)*3 + j/3;

                if(!box.containsKey(boxId)){
                    box.put(boxId, new HashSet<>());
                }

                if(board[i][j] != '.'){
                    rows.get(i).add(board[i][j]);
                    cols.get(j).add(board[i][j]);
                    box.get(boxId).add(board[i][j]);
                }   
            }
        }

        solve(board, rows, cols, box, 0, 0);
    }

    boolean solve(char[][] board, Map<Integer, 
    HashSet<Character>> rows, Map<Integer, 
    HashSet<Character>> cols, 
    Map<Integer, HashSet<Character>> box, int r, int c){
        int n = board.length;
        if(r >= n){
            return true;
        }

        if(board[r][c] != '.'){
            int nc = (c+1)%n;
            int nr = nc == 0 ? r+1 : r;
            return solve(board, rows, cols, box, nr, nc);
        }else{
            for(char i='1'; i<='9'; i++){
                int boxId = (r/3)*3 + c/3;
                if(rows.get(r).contains(i) || cols.get(c).contains(i) || box.get(boxId).contains(i)){
                    continue;
                }
                
                board[r][c] = i;
                rows.get(r).add(i);
                cols.get(c).add(i);
                box.get(boxId).add(i);

                int nc = (c+1)%n;
                int nr = nc == 0 ? r+1 : r;
                if(solve(board, rows, cols, box, nr, nc)){
                    return true;
                }

                board[r][c] = '.';
                rows.get(r).remove(i);
                cols.get(c).remove(i);
                box.get(boxId).remove(i);
            }
        }
        return false;
    }
}