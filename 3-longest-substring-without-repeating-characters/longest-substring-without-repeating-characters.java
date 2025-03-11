class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s== null || s.length() == 0){
            return 0;
        }

        int l=0;
        int r=1;
        Map<Character, Boolean> m = new HashMap<>();
        m.put(s.charAt(0), true);
        int res = 1;

        while(r<s.length()){
            Character c = s.charAt(r);
            if(m.containsKey(c) && m.get(c)){
                res = Math.max(res, r-l);
                while(c != s.charAt(l)){
                    m.put(s.charAt(l), false);
                    l++;
                }
                l++;
            }
            m.put(c, true);
            r++;
        }
        return Math.max(res, r-l);
    }
}