class Solution {
    public int shortestPath(int[][] grid, int k) {
        Queue<int[]> q = new LinkedList<>();
        
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = {{0,-1},{0,1},{1,0},{-1,0}};
        
        // 3D visited array: [row][col][eliminations_left]
        boolean[][][] visited = new boolean[m][n][k+1];
        
        q.offer(new int[]{0,0,0,k}); // {row, col, steps, eliminations_left}
        visited[0][0][k] = true; // Mark starting state as visited
        
        while(!q.isEmpty()){
            int[] cell = q.poll();
            int r = cell[0];
            int c = cell[1];
            int steps = cell[2];
            int eliminations = cell[3];
            
            // Check if we reached destination
            if(r == m-1 && c == n-1) return steps;
            
            for(int[] dir: dirs){
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if(nr >= 0 && nc >= 0 && nr < m && nc < n){
                    if(grid[nr][nc] == 1 && eliminations > 0){
                        // Moving through obstacle - use one elimination
                        if(!visited[nr][nc][eliminations-1]){
                            visited[nr][nc][eliminations-1] = true;
                            q.offer(new int[]{nr, nc, steps + 1, eliminations - 1});
                        }
                    } else if(grid[nr][nc] == 0){
                        // Moving through empty cell
                        if(!visited[nr][nc][eliminations]){
                            visited[nr][nc][eliminations] = true;
                            q.offer(new int[]{nr, nc, steps + 1, eliminations});
                        }
                    }
                }
            }
        }
        return -1;
    }
}