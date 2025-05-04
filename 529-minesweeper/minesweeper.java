// class Solution {
//     class Node{
//         int i;
//         int j;
//         char val;
//         Node(int i, int j, char val){
//             this.i = i;
//             this.j = j;
//             this.val = val;
//         }
        
//     }
//     public char[][] updateBoard(char[][] board, int[] click) {
//         int i = click[0];
//         int j = click[1];
//         if(board[i][j] == 'M'){
//             board[i][j] = 'X';
//             return board;
//         }

//         Queue<Node> q = new LinkedList<>();
//         q.offer(new Node(i,j,board[i][j]));

//         while(!q.isEmpty()){
//             Node n = q.poll();
//             if(n.val != 'E'){
//                 continue;
//             }
//             int r = n.i;
//             int c = n.j;
//             int cnt=0;
//             List<Node> arr = new ArrayList<>();

//             if(r-1>=0){
//                 if(board[r-1][c] == 'M'){
//                     cnt++;
//                 } else if(board[r-1][c] == 'E'){
//                     arr.add(new Node(r-1,c,board[r-1][c]));
//                 }
//             }
//             if(r-1>=0 && c+1<board[0].length){
//                 if(board[r-1][c+1] == 'M'){
//                     cnt++;
//                 } else if(board[r-1][c+1] == 'E'){
//                     arr.add(new Node(r-1,c+1,board[r-1][c+1]));
//                 }
//             }
//             if(c+1<board[0].length){
//                 if(board[r][c+1] == 'M'){
//                     cnt++;
//                 } else if(board[r][c+1] == 'E'){
//                     arr.add(new Node(r,c+1,board[r][c+1]));
//                 }
//             }
//             if(r+1<board.length && c+1<board[0].length){
//                 if(board[r+1][c+1] == 'M'){
//                     cnt++;
//                 } else if(board[r+1][c+1] == 'E'){
//                     arr.add(new Node(r+1,c+1,board[r+1][c+1]));
//                 }
//             }
//             if(r+1<board.length){
//                 if(board[r+1][c] == 'M'){
//                     cnt++;
//                 } else if(board[r+1][c] == 'E'){
//                     arr.add(new Node(r+1,c,board[r+1][c]));
//                 }
//             }
//             if(r+1<board.length && c-1>=0){
//                 if(board[r+1][c-1] == 'M'){
//                     cnt++;
//                 } else if(board[r+1][c-1] == 'E'){
//                     arr.add(new Node(r+1,c-1,board[r+1][c-1]));
//                 }
//             }
//             if(c-1>=0){
//                 if(board[r][c-1] == 'M'){
//                     cnt++;
//                 } else if(board[r][c-1] == 'E'){
//                     arr.add(new Node(r,c-1,board[r][c-1]));
//                 }
//             }
//             if(r-1>=0 && c-1>=0){
//                 if(board[r-1][c-1] == 'M'){
//                     cnt++;
//                 } else if(board[r-1][c-1] == 'E'){
//                     arr.add(new Node(r-1,c-1,board[r-1][c-1]));
//                 }
//             }

//             if(cnt == 0){
//                 board[r][c] = 'B';
//                 for(int x =0;x<arr.size();x++){
//                     q.offer(arr.get(x));
//                 }
//             } else{
//                 board[r][c] = String.valueOf(cnt).charAt(0);
//             }
            
//         }
//         return board;
//     }
// }

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int i = click[0];
        int j = click[1];
        
        // If mine, game over
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return board;
        }
        
        // Directions for all 8 adjacent cells
        int[][] dirs = {{-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}};
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1];
            
            if (board[r][c] != 'E') continue;
            
            // Count adjacent mines
            int mines = 0;
            List<int[]> neighbors = new ArrayList<>();
            
            for (int[] dir : dirs) {
                int newR = r + dir[0];
                int newC = c + dir[1];
                
                if (newR >= 0 && newR < board.length && newC >= 0 && newC < board[0].length) {
                    if (board[newR][newC] == 'M') {
                        mines++;
                    } else if (board[newR][newC] == 'E') {
                        neighbors.add(new int[]{newR, newC});
                    }
                }
            }
            
            // Update current cell
            if (mines > 0) {
                board[r][c] = (char)(mines + '0');
            } else {
                board[r][c] = 'B';
                // Add neighbors to queue only if current cell is 'B'
                for (int[] neighbor : neighbors) {
                    q.offer(neighbor);
                }
            }
        }
        
        return board;
    }
}
