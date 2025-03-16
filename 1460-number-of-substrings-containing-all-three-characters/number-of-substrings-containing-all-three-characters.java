class Solution {
    public int numberOfSubstrings(String s) {
        int i = 0;
        int a=-1;
        int b=-1;
        int c=-1;
        int count = 0;

        while(i<s.length()){
            if(s.charAt(i) == 'a'){
                a = i;
            } else if(s.charAt(i) == 'b'){
                b = i;
            } else{
                c = i;
            }

            if(a!=-1 && b!=-1 && c!=-1){
                count += Math.min(Math.min(a,b), c) + 1;
            }
            i++;
        }
        return count;
    }
}