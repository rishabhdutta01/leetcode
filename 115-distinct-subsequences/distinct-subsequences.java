class Solution {
    public int numDistinct(String s, String t) {
        Integer[][] dp = new Integer[s.length()][t.length()];
        return fnc(s,t,0,0,dp);
    }

    int fnc(String s, String t, int si, int ti, Integer[][] dp){
        if (ti == t.length()) {
            return 1;
        }
        if (si == s.length()) {
            return 0;
        }
        if (t.length() - ti > s.length() - si) {
            return 0;
        }
        
        if(dp[si][ti] != null) return dp[si][ti];

        int res = 0;
        if(s.charAt(si) == t.charAt(ti)){
            res += fnc(s,t,si+1,ti+1,dp);
        }
        
        res+= fnc(s,t,si+1,ti,dp);
        
        dp[si][ti] = res;
        return res;
    }
}