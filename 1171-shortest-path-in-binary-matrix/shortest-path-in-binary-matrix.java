class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;

        grid[0][0] = -1;
        Queue<int[]> q = new LinkedList<>();
        int[][] dirs = {{0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}, {-1,0}, {-1,1}};
        q.add(new int[]{0,0});

        while(!q.isEmpty()){
            int[] dim = q.poll();
            if(dim[0] == n-1 && dim[1] == n-1){
                break;
            }
            
            for(int[] dir: dirs){
                int r = dim[0]+dir[0];
                int c = dim[1]+dir[1];
                if(r>=0 && c>=0 && r<n && c<n && grid[r][c]==0){
                    // grid[r][c] = Math.max(grid[r][c], grid[dim[0]][dim[1]] - 1);
                    grid[r][c] = grid[dim[0]][dim[1]] - 1;
                    q.add(new int[] {r,c});
                }
            }
        }
        return grid[n-1][n-1] == 0 ? -1 : Math.abs(grid[n-1][n-1]);
    }
}