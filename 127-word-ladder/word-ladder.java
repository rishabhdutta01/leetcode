class Solution {
    public int ladderLength(
        String beginWord,
        String endWord,
        List<String> wordList
    ) {
        // Since all words are of same length.
        int L = beginWord.length();

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        Map<String, List<String>> allComboDict = new HashMap<>();

        wordList.forEach(word -> {
            for (int i = 0; i < L; i++) {
                // Key is the generic word
                // Value is a list of words which have the same intermediate generic word.
                String newWord =
                    word.substring(0, i) + '*' + word.substring(i + 1, L);
                List<String> transformations = allComboDict.getOrDefault(
                    newWord,
                    new ArrayList<>()
                );
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        });

        // Queue for BFS
        Queue<Pair<String, Integer>> Q = new LinkedList<>();
        Q.add(new Pair(beginWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            Pair<String, Integer> node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < L; i++) {
                // Intermediate words for current word
                String newWord =
                    word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(
                    newWord,
                    new ArrayList<>()
                )) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }

        return 0;
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