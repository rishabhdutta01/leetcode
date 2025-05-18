class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0];
        int c = click[1];

        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }

        int[][] dirs = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 } };
        Queue<int[]> q = new LinkedList<int[]>();

        q.offer(new int[] { r, c });

        while (!q.isEmpty()) {
            int[] dim = q.poll();
            r = dim[0];
            c = dim[1];

            if (board[r][c] != 'E') continue;

            int cnt = 0;
            List<int[]> l = new ArrayList<>();

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length) {
                    if (board[nr][nc] == 'M') {
                        cnt++;
                    } else if (board[nr][nc] == 'E') {
                        l.add(new int[]{nr, nc});
                    }
                }
            }

            if (cnt == 0) {
                board[r][c] = 'B';
                q.addAll(l);
                // for (int[] arr : l) {
                //     q.offerAll(new int[] { arr[0], arr[1] });
                // }
            } else {
                board[r][c] = (char) (cnt + '0');
            }
        }
        return board;
    }
}

// class Solution {
//     public char[][] updateBoard(char[][] board, int[] click) {
//         int i = click[0];
//         int j = click[1];

//         // If mine, game over
//         if (board[i][j] == 'M') {
//             board[i][j] = 'X';
//             return board;
//         }

//         // Directions for all 8 adjacent cells
//         int[][] dirs = {{-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}};

//         Queue<int[]> q = new LinkedList<>();
//         q.offer(new int[]{i, j});

//         while (!q.isEmpty()) {
//             int[] curr = q.poll();
//             int r = curr[0], c = curr[1];

//             if (board[r][c] != 'E') continue;

//             // Count adjacent mines
//             int mines = 0;
//             List<int[]> neighbors = new ArrayList<>();

//             for (int[] dir : dirs) {
//                 int newR = r + dir[0];
//                 int newC = c + dir[1];

//                 if (newR >= 0 && newR < board.length && newC >= 0 && newC < board[0].length) {
//                     if (board[newR][newC] == 'M') {
//                         mines++;
//                     } else if (board[newR][newC] == 'E') {
//                         neighbors.add(new int[]{newR, newC});
//                     }
//                 }
//             }

//             // Update current cell
//             if (mines > 0) {
//                 board[r][c] = (char)(mines + '0');
//             } else {
//                 board[r][c] = 'B';
//                 // Add neighbors to queue only if current cell is 'B'
//                 for (int[] neighbor : neighbors) {
//                     q.offer(neighbor);
//                 }
//             }
//         }

//         return board;
//     }
// }
