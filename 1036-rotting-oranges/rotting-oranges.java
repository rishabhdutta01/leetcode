class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        int cnt = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 2) q.add(new int[]{i,j});
                if(grid[i][j] == 1) cnt++;
            }
        }

        if (cnt == 0) return 0;

        int res = -1;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

        while(!q.isEmpty()){
            res++;
            int size = q.size();
            for(int i=0;i<size;i++){
                int[] cell = q.poll();
                for(int[] dir: dirs){
                    int nr = cell[0] + dir[0];
                    int nc = cell[1] + dir[1];
                    if(nr>=0 && nr<m && nc>=0 && nc<n && grid[nr][nc] == 1){
                        cnt--;
                        grid[nr][nc] = 2;
                        q.add(new int[]{nr,nc});
                    }
                }
            }
            
        }
        return cnt == 0 ?res:-1;
    }
}