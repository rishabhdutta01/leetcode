class Solution {
    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int houses = 0;
        int[][] dist = new int[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1){
                    dist[i][j] = Integer.MAX_VALUE;
                    bfs(grid,dist,houses,i,j);
                    houses++;
                }else if(grid[i][j]==2){
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == houses*-1){
                    res = Math.min(res, dist[i][j]);
                } 
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    void bfs(int[][] grid, int[][] dist, int houses, int row, int col){
        int[][] moves = {{-1,0},{0,1},{1,0},{0,-1}};
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{row,col});

        int steps = 0;
        while(!q.isEmpty()){
            steps++;
            int n = q.size();
            for(int i=0;i<n;i++){
                int[] dim = q.poll();
                for(int[] m:moves){
                    int nrow = dim[0]+m[0];
                    int ncol = dim[1]+m[1];
                    if(nrow>=0 && ncol>=0 && nrow<grid.length && ncol<grid[0].length){
                        if(grid[nrow][ncol]==houses*-1){
                            dist[nrow][ncol] += steps;
                            q.offer(new int[]{nrow,ncol});
                            grid[nrow][ncol]--;
                        }
                    }
                }
            }
        }
    }
}