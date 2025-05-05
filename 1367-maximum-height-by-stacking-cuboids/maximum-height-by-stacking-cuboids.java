// class Solution {
    
//     public int maxHeight(int[][] cuboids) {
//         int n=cuboids.length;
//         for(int i=0;i<n;i++){
//             Arrays.sort(cuboids[i]);
//         }

//         Arrays.sort(cuboids, (a,b) -> {
//             if(a[2]!=b[2])return b[2]-a[2];
//             if(a[1]!=b[1])return b[1]-a[1];
//             return b[0]-a[0];
//         });

        
//         int[] dp = new int[n];
//         dp[0] = cuboids[0][2];
//         int maxh = dp[0];
//         for(int i=1;i<n;i++){
//             dp[i] = cuboids[i][2];
//             for(int j=0;j<i;j++){
//                 if(cuboids[i][0]<=cuboids[j][0] && cuboids[i][1]<=cuboids[j][1] && cuboids[i][2]<=cuboids[j][2]){
//                     dp[i] = Math.max(dp[i],dp[j]+cuboids[i][2]);
//                 }
//                 maxh = Math.max(maxh,dp[i]);
//             }
//         }
//         return maxh;
//     }
// }


// class Solution {
//     int maxh = Integer.MIN_VALUE;
//     public int maxHeight(int[][] cuboids) {
//         int n = cuboids.length;
        
//         // Sort each cuboid's dimensions
//         for (int[] cuboid : cuboids) {
//             Arrays.sort(cuboid);
//         }
        
//         // Sort cuboids based on width, then length, then height
//         Arrays.sort(cuboids, (a, b) -> {
//             if (a[0] != b[0]) return b[0] - a[0];
//             if (a[1] != b[1]) return b[1] - a[1];
//             return b[2] - a[2];
//         });
        
//         fnc(cuboids,new boolean[n],Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0);
        
//         return maxh;
//     }

//     void fnc(int[][] cuboids, boolean[] taken, int lh, int ll, int lw, int h) {
//         maxh=Math.max(maxh,h);

//         for(int i=0;i<cuboids.length;i++){
//             if(!taken[i]){
//                 taken[i] = true;
//                 if(cuboids[i][0]<=lh && cuboids[i][1]<=ll && cuboids[i][2]<=lw){
//                     fnc(cuboids,taken,cuboids[i][0],cuboids[i][1],cuboids[i][2],h+cuboids[i][0]);
//                 }
//                 if(cuboids[i][0]<=lh && cuboids[i][2]<=ll && cuboids[i][1]<=lw){
//                     fnc(cuboids,taken,cuboids[i][0],cuboids[i][2],cuboids[i][1],h+cuboids[i][0]);
//                 }
//                 if(cuboids[i][1]<=lh && cuboids[i][0]<=ll && cuboids[i][2]<=lw){
//                     fnc(cuboids,taken,cuboids[i][1],cuboids[i][0],cuboids[i][2],h+cuboids[i][1]);
//                 }
//                 if(cuboids[i][1]<=lh && cuboids[i][2]<=ll && cuboids[i][0]<=lw){
//                     fnc(cuboids,taken,cuboids[i][1],cuboids[i][2],cuboids[i][0],h+cuboids[i][1]);
//                 }
//                 if(cuboids[i][2]<=lh && cuboids[i][0]<=ll && cuboids[i][1]<=lw){
//                     fnc(cuboids,taken,cuboids[i][2],cuboids[i][0],cuboids[i][1],h+cuboids[i][2]);
//                 }
//                 if(cuboids[i][2]<=lh && cuboids[i][1]<=ll && cuboids[i][0]<=lw){
//                     fnc(cuboids,taken,cuboids[i][2],cuboids[i][1],cuboids[i][0],h+cuboids[i][2]);
//                 }
//                 taken[i] = false;
//             }  
//         }
//         return;
//     }
// }

class Solution {
    public boolean isSafe(int[] c1,int[] c2) {
        if((c1[0] <= c2[0]) && (c1[1] <= c2[1]) && (c1[2] <= c2[2])) return true;
        return false;
    }

    public int helper(int[][] cuboids,int prev,int curr,int[][] dp) {
        // Base Case
        if(curr >= cuboids.length) return 0;
        if(dp[prev + 1][curr] != -1) return dp[prev + 1][curr];
        int include = 0;
        if(prev == -1 || isSafe(cuboids[curr],cuboids[prev])) {
            include = cuboids[curr][2] + helper(cuboids,curr,curr + 1,dp);
        }
        int exclude = 0 + helper(cuboids,prev,curr + 1,dp);
        dp[prev + 1][curr] = Math.max(include,exclude);
        return dp[prev + 1][curr];
    }

    public int maxHeight(int[][] cuboids) {
        int prev = -1;
        int curr = 0;
        for(int[] row : cuboids) {
            Arrays.sort(row);
        }

        Arrays.sort(cuboids,(a,b) -> {
            if(a[0] != b[0]) return b[0] - a[0];
            if(a[1] != b[1]) return b[1] - a[1];
            return b[2] - a[2];
        });

        int[][] dp = new int[cuboids.length][cuboids.length];
        for(int[] row : dp) {
            Arrays.fill(row,-1);
        }
        return helper(cuboids,prev,curr,dp);
    }
}