class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int res = 0;

        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j] == '1' && visited[i][j] == false) {
                    go(grid, visited, i, j);
                    res+=1;
                }
            }
        }
        return res;
    }

    void go(char[][] grid, boolean[][] visited, int i, int j) {
        if(i<0 || i>= grid.length || j<0 || j>= grid[0].length || grid[i][j] == '0' || visited[i][j] == true){
            return;
        }
        
        visited[i][j] = true;
        go(grid, visited, i,j-1);
        go(grid, visited, i,j+1);
        go(grid, visited, i+1,j);
        go(grid, visited, i-1,j);
    }
}