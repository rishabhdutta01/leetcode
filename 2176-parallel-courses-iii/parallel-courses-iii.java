class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<List<Integer>> arr = new ArrayList<>();
        for(int i=0;i<=n;i++){
            arr.add(new ArrayList<>());
        }

        int[] indegree = new int[n+1];
        for(int i=0;i<relations.length;i++){
            arr.get(relations[i][0]).add(relations[i][1]);
            indegree[relations[i][1]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=1;i<=n;i++){
            if(indegree[i] == 0) {
                q.add(i);
            }
        }

        int[] ftime = new int[n+1];
        int res = 0;
        while(!q.isEmpty()){
            int course = q.poll();
            ftime[course] = ftime[course] + time[course - 1];
            res = Math.max(res, ftime[course]);
            for(int next: arr.get(course)){
                indegree[next]--;
                ftime[next] = Math.max(ftime[next], ftime[course]);
                if(indegree[next] == 0) q.add(next);
            }            
        }
        return res;
    }
}