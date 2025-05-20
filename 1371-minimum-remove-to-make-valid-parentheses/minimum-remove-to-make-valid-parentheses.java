class Solution {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int r=0;
        int l=0;

        //Remove faulty close brackets
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='('){
                l++;
            } else if(c==')'){
                if(l>0) l--;
                else continue;
            }
            sb.append(c);
        }

        //Remove faulty open brackets
        int i = sb.length()-1;
        while(i>=0){
            if(sb.charAt(i)=='(' && l>0){
                l--;
                sb.deleteCharAt(i);
            } 
            i--;
        }

        return sb.toString();
    }
}