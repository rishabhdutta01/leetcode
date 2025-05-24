class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length() == 0){
            return 0;
        }
        if(s.length() == 1){
            return 1;
        }

        Map<Character, Integer> m = new HashMap<>();
        int l=0, r=1, res = 1;
        m.put(s.charAt(l), l);
        while(r<s.length()){
            if(!m.containsKey(s.charAt(r))){
                m.put(s.charAt(r), r);
                res = Math.max(res, r-l+1);
            } else{
                int target = m.get(s.charAt(r));
                while(l<=target){
                    m.remove(s.charAt(l));
                    l++;
                }
                m.put(s.charAt(r), r);
            }
            r++;
        }
        return res;
    }
}

// class Solution {
//     public int lengthOfLongestSubstring(String s) {
//         if(s== null || s.length() == 0){
//             return 0;
//         }

//         int l=0;
//         int r=1;
//         Map<Character, Boolean> m = new HashMap<>();
//         m.put(s.charAt(0), true);
//         int res = 1;

//         while(r<s.length()){
//             Character c = s.charAt(r);
//             if(m.containsKey(c) && m.get(c)){
//                 res = Math.max(res, r-l);
//                 while(c != s.charAt(l)){
//                     m.put(s.charAt(l), false);
//                     l++;
//                 }
//                 l++;
//             }
//             m.put(c, true);
//             r++;
//         }
//         return Math.max(res, r-l);
//     }
// }