class Solution {
    
    public int maxHeight(int[][] cuboids) {
        int n=cuboids.length;
        for(int i=0;i<n;i++){
            Arrays.sort(cuboids[i]);
        }

        Arrays.sort(cuboids, (a,b) -> {
            if(a[2]!=b[2])return b[2]-a[2];
            if(a[1]!=b[1])return b[1]-a[1];
            return b[0]-a[0];
        });

        
        int[] dp = new int[n];
        dp[0] = cuboids[0][2];
        int maxh = dp[0];
        for(int i=1;i<n;i++){
            dp[i] = cuboids[i][2];
            for(int j=0;j<i;j++){
                if(cuboids[i][0]<=cuboids[j][0] && cuboids[i][1]<=cuboids[j][1] && cuboids[i][2]<=cuboids[j][2]){
                    dp[i] = Math.max(dp[i],dp[j]+cuboids[i][2]);
                }
                maxh = Math.max(maxh,dp[i]);
            }
        }
        return maxh;
    }
}
    // void fnc(int[][] cuboids, boolean[] taken, int lh, int ll, int lw, int h) {
    //     maxh=Math.max(maxh,h);

    //     for(int i=0;i<cuboids.length;i++){
    //         if(!taken[i]){
    //             taken[i] = true;
    //             if(cuboids[i][0]<=lh && cuboids[i][1]<=ll && cuboids[i][2]<=lw){
    //                 fnc(cuboids,taken,cuboids[i][0],cuboids[i][1],cuboids[i][2],h+cuboids[i][0]);
    //             }
    //             if(cuboids[i][0]<=lh && cuboids[i][2]<=ll && cuboids[i][1]<=lw){
    //                 fnc(cuboids,taken,cuboids[i][0],cuboids[i][2],cuboids[i][1],h+cuboids[i][0]);
    //             }
    //             if(cuboids[i][1]<=lh && cuboids[i][0]<=ll && cuboids[i][2]<=lw){
    //                 fnc(cuboids,taken,cuboids[i][1],cuboids[i][0],cuboids[i][2],h+cuboids[i][1]);
    //             }
    //             if(cuboids[i][1]<=lh && cuboids[i][2]<=ll && cuboids[i][0]<=lw){
    //                 fnc(cuboids,taken,cuboids[i][1],cuboids[i][2],cuboids[i][0],h+cuboids[i][1]);
    //             }
    //             if(cuboids[i][2]<=lh && cuboids[i][0]<=ll && cuboids[i][1]<=lw){
    //                 fnc(cuboids,taken,cuboids[i][2],cuboids[i][0],cuboids[i][1],h+cuboids[i][2]);
    //             }
    //             if(cuboids[i][2]<=lh && cuboids[i][1]<=ll && cuboids[i][0]<=lw){
    //                 fnc(cuboids,taken,cuboids[i][2],cuboids[i][1],cuboids[i][0],h+cuboids[i][2]);
    //             }
    //             taken[i] = false;
    //         }  
    //     }
    //     return;
    // }

