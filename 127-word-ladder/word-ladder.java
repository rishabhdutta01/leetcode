class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet(wordList);
        if(!wordSet.contains(endWord)) return 0;

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        wordSet.remove(beginWord);
        int cnt = 1;

        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                String word = q.poll();
                char[] warr = word.toCharArray();
                for(int j=0; j<warr.length;j++){
                    char ch = warr[j];
                    for(char nch='a';nch<='z';nch++){
                        warr[j] = nch;
                        String nword = new String(warr);

                        if(endWord.equals(nword)) return cnt+1;

                        if(wordSet.contains(nword)){
                            wordSet.remove(nword);
                            q.offer(nword);
                        }
                    }
                    warr[j] = ch;
                }
            }
            cnt++;
        }
        return 0;
    }
}