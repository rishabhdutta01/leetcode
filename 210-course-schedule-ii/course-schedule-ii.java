class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];

        // 1. Create adj
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<Integer>());
        }

        for (int[] pre: prerequisites){
            adj.get(pre[1]).add(pre[0]);
            indegree[pre[0]]++;
        }

        // 2. Add all indegree = 0 nodes in queue
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++){
            if (indegree[i] == 0){
                queue.offer(i);
            }
        }

        // 3. BFS, every time remove one node with indegree = 0, 
        // decrement neighbors, add indegree = 0 in queue.
        // count nodes on the DAG. 

        int nodesVisited = 0;
        List<Integer> res = new ArrayList<>();

        while (!queue.isEmpty()){
            int node = queue.poll();
            res.add(node);
            nodesVisited++;
            
            
            for (int neighbor: adj.get(node)){
                // Delete the edge "node -> neighbor"
                indegree[neighbor]--;
                if (indegree[neighbor] == 0){
                    queue.offer(neighbor);
                }
            }
        }

        if(nodesVisited == numCourses){
            return res.stream().mapToInt(i -> i).toArray();
        } else{
            return new int[0];
        }
    }
}