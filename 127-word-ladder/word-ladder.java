class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord))
            return 0;

        Set<String> set = new HashSet<>();
        Queue<String> q = new LinkedList<>();

        q.add(beginWord);
        set.addAll(wordList);
        set.remove(beginWord);

        int dist = 1;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String w = q.poll();

                if (endWord.equals(w))
                    return dist;

                for(int j=0;j<w.length();j++){
                    for(char ch = 'a';ch<='z';ch++){
                        char[] arr = w.toCharArray();
                        arr[j] = ch;
                        String rw = new String(arr);
                        if(set.contains(rw)){
                            set.remove(rw);
                            q.add(rw);
                        }
                    }
                }
            }
            dist++;
        }
        return 0;
    }

    boolean adjacent(String w1, String w2) {
        if (w1.length() != w2.length())
            return false;

        int cnt = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                cnt++;
                if (cnt == 2)
                    return false;
            }
        }
        return cnt == 1;
    }
}
// class Solution {
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         if (beginWord.equals(endWord))
//             return 0;

//         Map<String, Set<String>> map = new HashMap<>();
//         Set<String> set = new HashSet<>();

//         for (int i = 0; i < wordList.size(); i++) {
//             if (adjacent(beginWord, wordList.get(i))) {
//                 if (!map.containsKey(beginWord)) {
//                     map.put(beginWord, new HashSet<>());
//                 }
//                 map.get(beginWord).add(wordList.get(i));
//             }

//             for (int j = 0; j < i; j++) {
//                 if (adjacent(wordList.get(i), wordList.get(j))) {
//                     if (!map.containsKey(wordList.get(i))) {
//                         map.put(wordList.get(i), new HashSet<>());
//                     }
//                     if (!map.containsKey(wordList.get(j))) {
//                         map.put(wordList.get(j), new HashSet<>());
//                     }
//                     map.get(wordList.get(i)).add(wordList.get(j));
//                     map.get(wordList.get(j)).add(wordList.get(i));
//                 }
//             }
//         }

//         Queue<String> q = new LinkedList<>();
//         if(map.containsKey(beginWord) && map.get(beginWord).size()>0) 
//             q.addAll(map.get(beginWord));
//         set.add(beginWord);
//         int dist = 1;

//         while (!q.isEmpty()) {
//             int size = q.size();
//             for (int i = 0; i < size; i++) {
//                 String w = q.poll();
//                 if (set.contains(w))
//                     continue;
//                 if (endWord.equals(w))
//                     return dist+1;

//                 set.add(w);
//                 if(map.containsKey(w) && map.get(w).size()>0) 
//                     q.addAll(map.get(w));
//             }
//             dist++;
//         }
//         return 0;
//     }

//     boolean adjacent(String w1, String w2) {
//         if (w1.length() != w2.length())
//             return false;

//         int cnt = 0;
//         for (int i = 0; i < w1.length(); i++) {
//             if (w1.charAt(i) != w2.charAt(i)) {
//                 cnt++;
//                 if (cnt == 2)
//                     return false;
//             }
//         }
//         return cnt == 1;
//     }
// }