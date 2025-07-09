class Solution {
    int m;
    int n;
    int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

    public int closedIsland(int[][] grid) {
        int res = 0;
        m = grid.length;
        n = grid[0].length;

        for(int i=1;i<m-1;i++){
            for(int j=1;j<n-1;j++){
                if(grid[i][j] == 1) continue;
                if(dfs(grid, i, j) == 1){
                    res++;
                }
            }
        }
        return res;
    }

    int dfs(int[][] grid, int r, int c) {
        int val = 1;
        grid[r][c] = 1;

        for(int[] dir: dirs){
            int nr = r + dir[0];
            int nc = c + dir[1];

            if((nr == 0 || nr == m-1 || nc == 0 || nc == n-1) && grid[nr][nc] == 0){
                val *= 0;
                continue;
            }

            if(grid[nr][nc] == 0) val *= dfs(grid, nr, nc);
        }
        return val;
    }
}