class Solution {
    int[] p;
    int[] s;
    public int countComponents(int n, int[][] edges) {
        p = new int[n];
        s = new int[n];

        for(int i=0;i<n; i++){
            p[i] = i;
            s[i] = 1;
        }

        for (int[] edge : edges) {
            int x1 = edge[0];
            int x2 = edge[1];
            union(x1, x2);
        }

        int cnt=0;
        for(int i=0;i<n; i++){
            System.out.println("parent" + i+":" + p[i]);
            System.out.println("size" + i+":" + s[i]);
            if(p[i] == i){
                cnt++;
            }
        }

        return cnt;
    }

    int find(int x){
        if (p[x] != x) {
            p[x] = find(p[x]); // Path compression for efficiency
        }
        return p[x];
    }

    void union(int x1, int x2) {
        x1 = find(x1);
        x2 = find(x2);

        if(x1==x2){
            return;
        } else if (s[x1] >= s[x2]) {
            p[x2] = x1;
            s[x1] += s[x2];
        } else {
            p[x1] = x2;
            s[x2] += s[x1];
        }
        return;
    }
}