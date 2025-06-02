class Solution {
    public String minRemoveToMakeValid(String s) {
        char[] arr = s.toCharArray();
        int open=0, opentotal=0, i=0, j=0;
        for(i=0;i<arr.length;i++){
            if(arr[i]=='('){
                open++;
                opentotal++;
            } else if(arr[i]==')'){
                if(open>0){
                    open--;       
                } else{
                    continue;   
                }
            }
            arr[j] = arr[i];
            j++;
        }
        int openkeep = opentotal - open;
        int n = j;
        j=0;  
        for(i=0;i<n;i++){
            if(arr[i]=='('){
                if(openkeep > 0){
                    openkeep--;
                } else continue;
            } 
            arr[j] = arr[i];
            j++;
        }
        return new String(arr, 0, j);
    }
}

// Time - O(n), space - O(n)
// class Solution {
//     public String minRemoveToMakeValid(String s) {
//         StringBuilder sb = new StringBuilder();
//         int r=0;
//         int l=0;

//         //Remove faulty close brackets
//         for(int i=0;i<s.length();i++){
//             char c = s.charAt(i);
//             if(c=='('){
//                 l++;
//             } else if(c==')'){
//                 if(l>0) l--;
//                 else continue;
//             }
//             sb.append(c);
//         }

//         //Remove faulty open brackets
//         int i = sb.length()-1;
//         while(i>=0){
//             if(sb.charAt(i)=='(' && l>0){
//                 l--;
//                 sb.deleteCharAt(i);
//             } 
//             i--;
//         }

//         return sb.toString();
//     }
// }