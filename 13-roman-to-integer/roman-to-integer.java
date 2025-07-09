class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> m = new HashMap<>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);

        int n = s.length();
        int i=0;
        int res = 0;
        while(i<n-1){
            char c = s.charAt(i);
            char nc = s.charAt(i+1);
            if(c == nc){
                int cnt = 1;
                while(i<n-1 && s.charAt(i) == s.charAt(i+1)){
                    cnt++;
                    i++;
                }
                res += cnt * m.get(s.charAt(i));
                i++;
            } else{
                if(m.get(c) > m.get(nc)){
                    res += m.get(c);
                    i++;
                }else{
                    res+= m.get(nc) - m.get(c);
                    i+=2;
                }
            }
        }
        if(i==n-1) res+=m.get(s.charAt(n-1));
        return res;
    }
}