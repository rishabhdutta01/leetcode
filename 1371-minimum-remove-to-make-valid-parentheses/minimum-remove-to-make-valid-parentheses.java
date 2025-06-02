class Solution {
    public String minRemoveToMakeValid(String s) {
        if(s==null || s.length()==0) return "";

        int open=0;
        int close=0;
        int opentotal=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '('){
                open++;
                opentotal++;
            } else if(s.charAt(i) == ')'){
                if(open==0) close++;
                else open--;
            }
        }

        StringBuilder sb = new StringBuilder();
        int openseen=0;
        int closedseen=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '('){
                if(opentotal != open) {
                    openseen++;
                    sb.append(s.charAt(i));
                    opentotal--;
                }
            } else if(s.charAt(i) == ')'){
                if(openseen==0) close--;
                else {
                    openseen--;
                    sb.append(s.charAt(i));
                }
            }else{
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}