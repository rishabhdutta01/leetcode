class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m + 1][n + 1];

        // Base case initialization
        for (int j = 0; j <= n; j++) {
            dp[m][j] = 0;
        }

        // Base case initialization
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                dp[i][j] = dp[i+1][j];

                if(s.charAt(i) == t.charAt(j)){
                    dp[i][j] += dp[i+1][j+1];
                }
            }
        }
        
        return dp[0][0];
    }
}

// class Solution {
//     public int numDistinct(String s, String t) {
//         Integer[][] dp = new Integer[s.length()][t.length()];
//         return fnc(s,t,0,0,dp);
//     }

//     int fnc(String s, String t, int si, int ti, Integer[][] dp){
//         if (ti == t.length()) {
//             return 1;
//         }
//         if (si == s.length()) {
//             return 0;
//         }
//         if (t.length() - ti > s.length() - si) {
//             return 0;
//         }
        
//         if(dp[si][ti] != null) return dp[si][ti];

//         int res = 0;
//         if(s.charAt(si) == t.charAt(ti)){
//             res += fnc(s,t,si+1,ti+1,dp);
//         }
        
//         res+= fnc(s,t,si+1,ti,dp);
        
//         dp[si][ti] = res;
//         return res;
//     }
// }