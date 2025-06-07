class Solution {
    public String customSortString(String order, String s) {
        int[] arr = new int[26];
        for(int i=0;i<s.length();i++){
            arr[s.charAt(i) - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<order.length();i++){
            sb.append(String.valueOf(order.charAt(i)).repeat(arr[order.charAt(i) - 'a']));
            arr[order.charAt(i) - 'a'] = 0;
        }

        for(int i=0;i<26;i++){
            if(arr[i] == 0) continue;
            sb.append(String.valueOf((char)('a' + i)).repeat(arr[i]));
        }
        return sb.toString();
    }
}