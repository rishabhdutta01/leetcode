class Solution {
    public int countSubstrings(String s) {
        if (s==null || s.length() == 0)
            return 0;
        if (s.length() == 1)
            return 1;

        int n =s.length();

        char[] arr = s.toCharArray();

        int res =0;
        
        boolean[][] dp = new boolean[n][n];

        for(int i=0;i<n;i++) {
            dp[i][i] = true;
            res++;
        }

        for(int i=0;i<n-1;i++){
            if(arr[i]==arr[i+1]){
                dp[i][i+1]=true;
                res++;
            }        
            else
                dp[i][i+1] = false;
        }

        for(int j=2;j<n;j++){
            for(int i=0;i<n-j;i++){
                int k=i+j;
                if(arr[i] == arr[k] && dp[i+1][k-1] == true) {
                    dp[i][k] = true;
                    res++;
                }
            else
                dp[i][k] = false;
            }            
        }
        return res;
    }
}