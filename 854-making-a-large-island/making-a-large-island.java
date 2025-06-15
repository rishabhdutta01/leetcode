class Solution {
    
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int cnt = 2;
        Map<Integer, Integer> map = new HashMap();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1){
                    map.put(cnt,dfs(grid, i, j, cnt));
                    cnt++;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 0){
                    
                    int[][] dirs ={{0,1},{1,0},{0,-1},{-1,0}};                    
                    Set<Integer> set = new HashSet<>();
                    for(int[] dir: dirs){
                        int nr = i + dir[0];
                        int nc = j + dir[1];
                        if(nr>=0 && nc>=0 && nr<n && nc<n && grid[nr][nc] != 0){
                            set.add(grid[nr][nc]);
                        }
                    }
                    int val=0;
                    for(int id: set){
                        if(map.containsKey(id)) val += map.get(id);
                    }
                    max = Math.max(max, val+1);
                }
            }
        }

        if(max == Integer.MIN_VALUE) return map.get(2);
        return max;
    }

    int dfs(int[][] grid, int r, int c, int id){
        grid[r][c] = id;
        int[][] dirs ={{0,1},{1,0},{0,-1},{-1,0}};
        int cnt = 1;
        for(int[] dir: dirs){
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr>=0 && nc>=0 && nr<grid.length && nc<grid[0].length && grid[nr][nc] == 1){
                cnt += dfs(grid, nr,nc,id);
            }
        }
        return cnt;
    }

    
}