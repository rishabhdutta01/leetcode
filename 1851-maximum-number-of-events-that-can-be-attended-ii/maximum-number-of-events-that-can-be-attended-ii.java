class Solution {
    int[][] res;
    public int maxValue(int[][] events, int k) {
        int n = events.length;
        res = new int[n][k+1];
        for(int i=0;i<n;i++){
            Arrays.fill(res[i],-1);
        }
        Arrays.sort(events, (a,b) -> a[0]!=b[0] ? a[0] - b[0] : a[1] - b[1]);
        return dfs(events, 0, k);        
    }

    int dfs(int[][] events, int i, int k){
        if(i==events.length || k==0) return 0;

        if(res[i][k] != -1) return res[i][k];

        int pick = events[i][2] + dfs(events, search(events,i+1), k-1);
        int notPick = dfs(events, i+1, k);
        res[i][k] = Math.max(pick,notPick);
        return res[i][k];
    }

    int search(int[][] events, int l){
        int t = events[l-1][1];
        int ans = events.length;
        int r = events.length-1;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(events[mid][0] > t){
                ans = mid;
                r = mid-1;
            } else l=mid+1;
        }
        return ans == events.length ? events.length : ans;
    }
}