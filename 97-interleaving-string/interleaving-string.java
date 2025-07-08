class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int l = s3.length();

        if(l != m + n) return false;

        Map<String, Boolean> map = new HashMap<>();
        return interleaved(s1,s2,s3,0,0,0, map);
    }

    boolean interleaved(String s1, String s2, String s3, int i1, int i2, int i3, Map<String, Boolean> m){
        if(i3 == s3.length()) return true;
        boolean res = false;
        if(i1!=s1.length() && i2!=s2.length() && s1.charAt(i1) == s3.charAt(i3) && s2.charAt(i2) == s3.charAt(i3)){
            String k1 = (i1+1) + "s1+" + (i3+1) + "s3";
            String k2 = (i2+1) + "s2+" + (i3+1) + "s3";
            boolean v1 = m.containsKey(k1) ? m.get(k1) : interleaved(s1,s2,s3,i1+1,i2,i3+1,m);
            boolean v2 = m.containsKey(k2) ? m.get(k2) : interleaved(s1,s2,s3,i1,i2+1,i3+1,m);
            res = v1 || v2;
        } else if(i1!=s1.length() && s1.charAt(i1) == s3.charAt(i3)){
            String k1 = (i1+1) + "s1+" + (i3+1) + "s3";
            res = m.containsKey(k1) ? m.get(k1) : interleaved(s1,s2,s3,i1+1,i2,i3+1,m);
        } else if(i2!=s2.length() && s2.charAt(i2) == s3.charAt(i3)){
            String k2 = (i2+1) + "s2+" + (i3+1) + "s3";
            res = m.containsKey(k2) ? m.get(k2) : interleaved(s1,s2,s3,i1,i2+1,i3+1,m);
        } else{
            res = false;
        }
        String k1 = (i1) + "s1+" + (i3) + "s3";
        String k2 = (i2) + "s2+" + (i3) + "s3";
        m.put(k1, res);
        m.put(k2, res);
        return res;
    }
}