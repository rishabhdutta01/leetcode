class Solution {
    public boolean queryString(String s, int n) {
        // Early exit: If n is too large compared to string length
        // A string of length m can only contain all binary numbers up to ~2m
        if (n > 2 * s.length()) {
            return false;
        }
        
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