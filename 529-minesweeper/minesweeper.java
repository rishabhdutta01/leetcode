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
