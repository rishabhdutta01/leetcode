class Solution {
    int inf = 2147483647;
    public void wallsAndGates(int[][] rooms) {
        
        Queue<Pair<Integer,Integer>> q = new LinkedList<>();
        boolean[][] visited = new boolean[rooms.length][rooms[0].length];

        for(int i=0;i<rooms.length;i++){
            for(int j=0;j<rooms[0].length;j++){
                if(rooms[i][j] == 0){
                    q.offer(new Pair(i,j));
                    visited[i][j] = true;
                }
            }
        }

        if(q.isEmpty()){
            return;
        }

        int[][] moves = {{-1,0},{0,1},{1,0},{0,-1}};
        

        while(!q.isEmpty()){
            int n = q.size();
            for(int i=0;i<n;i++){
                Pair<Integer,Integer> p = q.poll();
                int row = p.getKey();
                int col = p.getValue();
                for(int j=0;j<moves.length;j++){
                    int nrow = row+moves[j][0];
                    int ncol = col+moves[j][1];
                    // if(nrow>=0 && nrow<rooms.length && ncol>=0 && ncol<rooms[0].length && rooms[nrow][ncol]!=-1 && !visited[nrow][ncol]){
                    if(nrow>=0 && nrow<rooms.length && ncol>=0 && ncol<rooms[0].length && rooms[nrow][ncol]==inf){
                        rooms[nrow][ncol] = rooms[row][col]+1;
                        q.offer(new Pair(nrow,ncol));
                        // visited[nrow][ncol] = true;
                    }
                }   
            }
        }
    }
}