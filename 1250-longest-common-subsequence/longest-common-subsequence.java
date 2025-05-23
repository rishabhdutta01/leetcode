class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int dp[][] = new int[m+1][n+1];
        
        for(int i=0;i<m;i++){
            dp[i][0] = 0;
        }
        for(int j=0;j<n;j++){
            dp[0][j] = 0;
        }

        int[] prev = new int[n+1];
        int [] curr = new int[n+1];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(text1.charAt(i-1) == text2.charAt(j-1)){
                    curr[j] = 1 + prev[j-1];
                } else{
                    curr[j] = Math.max(curr[j-1], prev[j]);
                }               
            }
            prev = curr;
            curr = new int[n+1];
        }
        return prev[n];
    }
}