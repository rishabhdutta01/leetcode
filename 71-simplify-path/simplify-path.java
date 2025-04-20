class Solution {
    public String simplifyPath(String path) {
        Stack<String> st = new Stack<>();
        String[] arr = path.split("/");

        for(int i=0;i<arr.length;i++){
            if(arr[i].equals("") || arr[i].equals(".")){
                continue;
            }
            else if(arr[i].equals("..")){
                if(!st.isEmpty()){
                    st.pop();
                }
            }
            else{
                st.push(arr[i]);
            }
        }
        StringBuilder ans = new StringBuilder();
        ans.append("/");
        for(int i=0;i<st.size();i++){
            ans.append(st.get(i));
            ans.append("/");
        }
        ans.deleteCharAt(ans.length()-1);
        if(ans.isEmpty()){
            ans.append("/");
        }
        return ans.toString();
    }
}