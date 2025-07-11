class Solution {
    int[] parent;
    int[] rank;
    public int earliestAcq(int[][] logs, int n) {
        int[] parent = new int[n];
        int[] rank = new int[n];
        int cnt = n;

        Arrays.sort(logs, (a,b) -> a[0] - b[0]);

        for(int i=0;i<n;i++){
            parent[i] = i;
            rank[i] = 1;
        }

        for(int[] log: logs){
            int t = log[0];
            int a = log[1];
            int b = log[2];

            cnt -= join(a,b, parent, rank);

            if(cnt == 1){
                return t;
            }
        }
        return -1;
    }

    int parent(int x, int[] p){
        if(p[x] != x){
            p[x] = parent(p[x], p);
        }
        return p[x];
    }

    int join(int a, int b, int[] p, int[] r){
        int pa = parent(a, p);
        int pb = parent(b, p);

        if(pa == pb) return 0;

        if(r[pa] >= r[pb]){
            p[pb] = pa;
            r[pa] += r[pb];
        } else{
            p[pa] = pb;
            r[pb] += r[pa];
        }
        return 1;
    }
}