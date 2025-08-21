//2ms
// class Solution {
//     public String minWindow(String s, String t) {
//         Map<Character, Integer> map = new HashMap<>();
//         for (int i = 0; i < t.length(); i++) {
//             map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
//         }

//         int l = 0;
//         int r = 0;
//         int res = Integer.MAX_VALUE;
//         int ansl = 0;
//         int ansr = 0;
//         int rem = map.size();

//         while (r < s.length()) {
//             if (map.containsKey(s.charAt(r))) {
//                 map.put(s.charAt(r), map.get(s.charAt(r)) - 1);
//                 if (map.get(s.charAt(r)) == 0) {
//                     rem--;
//                 }
//             }

//             while (rem == 0) {
//                 if (res > r - l + 1) {
//                     res = r - l + 1;
//                     ansl = l;
//                     ansr = r;
//                 }

//                 if (map.containsKey(s.charAt(l))) {
//                     map.put(s.charAt(l), map.get(s.charAt(l)) + 1);

//                     if (map.get(s.charAt(l)) > 0) {
//                         rem++;
//                     }
//                 }
//                 l++;
//             }

//             r++;

//         }
//         return res == Integer.MAX_VALUE ? "" : s.substring(ansl, ansr+1);
//     }
// }

// class Solution {
//     public String minWindow(String s, String t) {
//         int[] map = new int[128];
//         for (char c : t.toCharArray()) {
//             map[c]++;
//         }
//         int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
//         while (end < s.length()) {
//             final char c1 = s.charAt(end);
//             if (map[c1] > 0)
//                 counter--;
//             map[c1]--;
//             end++;
//             while (counter == 0) {
//                 if (minLen > end - start) {
//                     minLen = end - start;
//                     minStart = start;
//                 }
//                 final char c2 = s.charAt(start);
//                 map[c2]++;
//                 if (map[c2] > 0)
//                     counter++;
//                 start++;
//             }
//         }

//         return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
//     }
// }

class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> tmap = new HashMap<>();
        for(char c: t.toCharArray()){
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        }

        int req = t.length();
        int l = 0;
        int len = Integer.MAX_VALUE;
        int minl = 0;

        for(int r = 0;r<s.length();r++){
            char c = s.charAt(r);
            if(tmap.containsKey(c)){
                 if(tmap.get(c)>0) req--;
                 tmap.put(c, tmap.get(c) -1);
            }

            while(req == 0){
                if(r-l+1 < len){
                    len = r-l+1;
                    minl=l;
                }
                char lc = s.charAt(l);
                if(tmap.containsKey(lc)){
                    tmap.put(lc, tmap.get(lc) + 1);
                    if(tmap.get(lc)>0) req++;
                }
                l++;
            }
        }
        
        return len == Integer.MAX_VALUE ? "" : s.substring(minl, minl+ len);
    }
}