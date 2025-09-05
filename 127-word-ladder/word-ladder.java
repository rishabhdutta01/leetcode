// class Solution {
//     public int ladderLength(String beginWord, String endWord, List<String> wordList) {
//         Set<String> wordSet = new HashSet(wordList);
//         if(!wordSet.contains(endWord)) return 0;

//         Queue<String> q = new LinkedList<>();
//         q.offer(beginWord);
//         wordSet.remove(beginWord);
//         int cnt = 1;

//         while(!q.isEmpty()){
//             int size = q.size();
//             for(int i=0;i<size;i++){
//                 String word = q.poll();
//                 char[] warr = word.toCharArray();
//                 for(int j=0; j<warr.length;j++){
//                     char ch = warr[j];
//                     for(char nch='a';nch<='z';nch++){
//                         warr[j] = nch;
//                         String nword = new String(warr);

//                         if(endWord.equals(nword)) return cnt+1;

//                         if(wordSet.contains(nword)){
//                             wordSet.remove(nword);
//                             q.offer(nword);
//                         }
//                     }
//                     warr[j] = ch;
//                 }
//             }
//             cnt++;
//         }
//         return 0;
//     }
// }

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> word = new HashSet<>(wordList);
        if(!word.contains(endWord)) return 0;
        begin.add(beginWord);
        end.add(endWord);
        return dfs(begin, end, word, 1);
    }

    public int dfs(Set<String> begin, Set<String> end, Set<String> word, int res){
        if(begin.size() == 0) return 0;
        if(begin.size() > end.size()) return dfs(end, begin, word, res);

        for(String w : begin) word.remove(w);

        Set<String> next = new HashSet<>();
        
        for(String curr : begin){
            char[] ch = curr.toCharArray();
            for(int i = 0; i < ch.length; i++){
                char c = ch[i];
                for(char j = 'a'; j <= 'z'; j++){
                    ch[i] = j;
                    String neighbor = new String(ch);
                    if(word.contains(neighbor)){
                        if(end.contains(neighbor)) return res + 1;
                        next.add(neighbor);
                    }
                    ch[i] = c;
                }
            }
        }
        return dfs(next, end, word, res + 1);
    }
}