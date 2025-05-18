class Solution {
    public boolean queryString(String s, int n) {
        int i = 0;
        while(i<s.length() && s.charAt(i) == '0'){
            i++;
        }

        if(i==s.length()) return false;
        

        StringBuilder sb = new StringBuilder(s.substring(i, s.length()));
        sb=sb.reverse();
        
        Set<Integer> set = new HashSet<>();
        for(i=0;i<sb.length();i++){
            int num = 0;
            for(int j=i;j<sb.length();j++){
                if(sb.charAt(j) == '0') continue;

                num+=Math.pow(2,j-i);
                set.add(num);
            }
        }

        for(i=1;i<=n;i++){
            if(!set.contains(i)) return false;
        }
        return true;
    }
}