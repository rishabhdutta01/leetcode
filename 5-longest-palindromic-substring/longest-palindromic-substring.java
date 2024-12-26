class Solution {
    public String longestPalindrome(String str) {
        
        int n=str.length();
        char[] arr = str.toCharArray();

        boolean dp[][]=new boolean[n][n];

        for(int i=0;i<n;i++){
                dp[i][i]=true;
        }

        int[] ans = new int[]{0, 0};

        for(int i=0;i<n-1;i++){
            if(arr[i]==arr[i+1]){
                dp[i][i+1]=true;
                ans[0]=i;
                ans[1]=i+1;
            }
        }

        for(int k=2;k<n;k++){
            for(int i=0;i<n-k;i++){
                int j=i+k;
                if(arr[i]==arr[j] && dp[i+1][j-1]){
                    dp[i][j]=true;
                    ans[0]=i;
                    ans[1]=j;
                }
            }
        }
         return str.substring(ans[0],ans[1]+1);
       
    }
}