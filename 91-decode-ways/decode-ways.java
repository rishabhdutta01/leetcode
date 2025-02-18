class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length()==0){
            return 0;
        }
        Map<Integer, Integer> dp = new HashMap<>();
    
        dp.put(s.length(), 1);

        return find(s, 0, dp);
    }

    public int find(String s, int i, Map<Integer, Integer> dp) {
        if(dp.get(i) != null){
            return dp.get(i);
        }
        if(s.charAt(i) == '0') {
            return 0;
        }

        int res = find(s,i+1,dp);

        if(i+1 < s.length() && (s.charAt(i) == '1' || (s.charAt(i) == '2' && ('6' - s.charAt(i+1) >=0)))) {
            res += find(s,i+2,dp);
        }
        dp.put(i, res);
        return res;

    }
}