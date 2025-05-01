class Solution {
    class Node {
        int value;
        boolean visited;
        List<Node> children;
        public Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses == 0 || numCourses == 1) {
            return true;
        }
        if(prerequisites == null || prerequisites.length ==0) {
            return true;
        }

        Node[] n = new Node[numCourses];
        int[] in = new int[numCourses];

        for(int i = 0; i<prerequisites.length; i++) {
            Node subject = null;
            if(n[prerequisites[i][0]] == null) {
                subject = new Node(prerequisites[i][0]);
                n[prerequisites[i][0]] = subject;
            } else {
                subject = n[prerequisites[i][0]];
            }

            Node prereq = null;
            if(n[prerequisites[i][1]] == null) {
                prereq = new Node(prerequisites[i][1]);
                n[prerequisites[i][1]] = prereq;
            } else {
                prereq = n[prerequisites[i][1]];
            }
            
            in[prerequisites[i][1]]++;
            subject.children.add(prereq);
        }

        Queue<Integer> q = new LinkedList<>();
        List<Integer> toposort = new ArrayList<>();

        for(int i=0;i<in.length;i++){
            if(in[i] == 0){
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
           int x= q.poll();
            Node parent = n[x];
            if(parent == null){
                toposort.add(x);
                continue;
            }
            toposort.add(parent.value);
            for(Node curr: parent.children) {
                in[curr.value]--;
                if(in[curr.value]==0){
                    q.offer(curr.value);
                }
            }
        }

        if(toposort.size()!=n.length)
            return false;
        return true;
    }
}