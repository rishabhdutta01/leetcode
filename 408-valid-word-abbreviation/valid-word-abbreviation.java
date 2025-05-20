class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i=0;
        int j=0;
        int num=0;
        while(i<word.length() && j<abbr.length()){
            if(!Character.isDigit(abbr.charAt(j))){
                if(word.charAt(i) == abbr.charAt(j)){
                    i++;
                    j++;
                    continue;
                } else return false;
            } else{
                if(num==0 && abbr.charAt(j) == '0') return false;
                while(j<abbr.length() && Character.isDigit(abbr.charAt(j))){
                    num = num*10 + abbr.charAt(j)-'0';
                    j++;
                }
                if(i+num-1<word.length()){
                    i = i+num;
                    num=0;
                } else return false;            
            }
        }

        if(i!=word.length() || j!=abbr.length()) return false;
        return true;
    }
}