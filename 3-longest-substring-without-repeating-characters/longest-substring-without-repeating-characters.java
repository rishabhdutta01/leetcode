class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null  || s.length() == 0) return 0;

        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
        int cnt = 1;
        int l=0;
        for(int r=1;r<n;r++){
            char c = s.charAt(r);
            if(!map.containsKey(c)){
                map.put(c, r);
                cnt = Math.max(cnt, map.size());
            } else{
                int idx = map.get(c);
                while(l<=idx){
                    map.remove(s.charAt(l));
                    l++;
                }
                map.put(c, r);
            }
        }
        return cnt;
    }
}