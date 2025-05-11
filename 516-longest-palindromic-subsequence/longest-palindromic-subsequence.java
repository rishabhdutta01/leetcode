class Solution {
    public int longestPalindromeSubseq(String s) {
        String rev = new StringBuilder(s).reverse().toString();

        int m = s.length();

        int n = rev.length();
        
        int[] prev = new int[n+1];
        int [] curr = new int[n+1];

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1) == rev.charAt(j-1)){
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