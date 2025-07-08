class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int l = s3.length();

        if(l != m + n) return false;

        Boolean[][] map = new Boolean[n+1][m+1];
        return interleaved(s1,s2,s3,0,0, map);
    }

    boolean interleaved(String s1, String s2, String s3, int i, int j, Boolean[][] map){
        if(i+j == s3.length()) return true;
        if(map[i][j]!=null) return map[i][j];

        boolean res = false;
        if(i!=s1.length() && j!=s2.length() && s1.charAt(i) == s3.charAt(i+j) && s2.charAt(j) == s3.charAt(i+j)){
            res = interleaved(s1,s2,s3,i+1,j,map) || interleaved(s1,s2,s3,i,j+1,map);
        } else if(i!=s1.length() && s1.charAt(i) == s3.charAt(i+j)){
            res = interleaved(s1,s2,s3,i+1,j,map);
        } else if(j!=s2.length() && s2.charAt(j) == s3.charAt(i+j)){
            res = interleaved(s1,s2,s3,i,j+1,map);
        } else{
            res = false;
        }
        map[i][j] = res;
        return res;
    }
}