class Solution {
    public String simplifyPath(String path) {
        if(path == null){
            return "/";
        }
        String[] arr = path.split("/");
        if(arr.length == 0){
            return "/";
        }
        
        Stack<String> s = new Stack<>();
        for(int i = arr.length-1; i>=0; i--){
            if(".".equals(arr[i])){
                continue;
            } else if ("..".equals(arr[i])){
                s.push(arr[i]);
            } else if("".equals(arr[i])) {
                 continue;
            } else {
                if(s.isEmpty()){
                    s.push(arr[i]);
                } else {
                    if("..".equals(s.peek())){
                        s.pop();
                        continue;
                    } else {
                        s.push(arr[i]);
                    }
                }
            }
        }
        if(s.isEmpty()){
            return "/";
        }

        StringBuilder sb = new StringBuilder("/");
        
        int n = s.size();
        for(int i = 0; i<n-1;i++){
            if("..".equals(s.peek())){
                s.pop();
                continue;
            }
            sb.append(s.pop());
            sb.append("/");
        }

        if("..".equals(s.peek())){
            s.pop();
        } else {
            sb.append(s.pop());
        }
        return sb.toString();
    }
}