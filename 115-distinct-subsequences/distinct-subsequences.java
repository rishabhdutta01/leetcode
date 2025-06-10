class Solution {
    public int numDistinct(String s, String t) {
        Map<String,Integer> map = new HashMap<>();
        return fnc(s,t,0,0,map);
    }

    int fnc(String s, String t, int si, int ti, Map<String,Integer> map){
        if(si == s.length()){
            if(ti == t.length()) return 1;
            else return 0;
        }

        if(ti == t.length()) return 1;

        String key = si + "," + ti;
        if(map.containsKey(key)) return map.get(key);

        int res = 0;
        for(int i=si;i<s.length();i++){
            if(s.length() - i < t.length() - ti) break;
            if(s.charAt(i) != t.charAt(ti)){
                continue;
            }else{
                res += fnc(s,t,i+1,ti+1,map);
            }
        }
        map.put(key, res);
        return res;
    }
}