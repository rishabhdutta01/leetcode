class Solution {
    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[0] - a[0]);

        for(int i=0;i<points.length;i++){
            if(pq.size()==k) {
                if(pq.peek()[0] > dist(points[i])){
                    pq.poll();
                    pq.add(new int[]{dist(points[i]), i});
                }
                
            } else{
                pq.add(new int[]{dist(points[i]), i});
            }

            
        }

        int[][] res = new int[k][2];
        int i=0;
        while(!pq.isEmpty()){
            int[] x = pq.poll();
            res[i++] = points[x[1]];
        }
        return res;
    }

    int dist(int[] p){
        return p[0]*p[0] + p[1]*p[1];
    }
}