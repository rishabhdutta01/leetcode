class Solution {
    int inf = 2147483647;
    public void wallsAndGates(int[][] rooms) {
        
        Queue<int[]> q = new LinkedList<>();

        for(int i=0;i<rooms.length;i++){
            for(int j=0;j<rooms[0].length;j++){
                if(rooms[i][j] == 0){
                    q.offer(new int[]{i,j});
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
                int[] dim = q.poll();
                for(int j=0;j<moves.length;j++){
                    int nrow = dim[0]+moves[j][0];
                    int ncol = dim[1]+moves[j][1];
                    if(nrow>=0 && nrow<rooms.length && ncol>=0 && ncol<rooms[0].length && rooms[nrow][ncol]==inf){
                        rooms[nrow][ncol] = rooms[dim[0]][dim[1]]+1;
                        q.offer(new int[]{nrow,ncol});
                    }
                }   
            }
        }
    }
}