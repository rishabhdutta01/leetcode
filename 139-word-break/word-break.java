class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s==null || s.length()==0 || wordDict==null || wordDict.isEmpty()) {
            return false;
        }

        int n= s.length();

        boolean[] dp = new boolean[n+1];
        Arrays.fill(dp,false);
        dp[0] = true;

        for(int i=1;i<=n;i++) {
            for(String word: wordDict) {
                if(i-word.length() < 0) {
                    continue;
                }

                if(dp[i-word.length()] && s.substring(i-word.length(), i).equals(word)){
                    dp[i]=true;
                }
            }
        }
        return dp[n];

    }
}