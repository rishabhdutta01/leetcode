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


class Solution {
    public String minWindow(String s, String t) {
        int [] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
              final char c1 = s.charAt(end);
              if (map[c1] > 0) counter--;
              map[c1]--;
              end++;
              while (counter == 0) {
                    if (minLen > end - start) {
                          minLen = end - start;
                          minStart = start;
                    }
                    final char c2 = s.charAt(start);
                    map[c2]++;
                    if (map[c2] > 0) counter++;
                    start++;
              }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}
