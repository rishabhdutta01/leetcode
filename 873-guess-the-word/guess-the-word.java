/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */


class Solution {
    public int match(String s1, String s2) {
        int n = s1.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                cnt++;
            }
        }
        
        return cnt;
    }

    public void findSecretWord(String[] words, Master master) {
        int n = words.length;
        int idx = (int) (Math.random() * n);
        int val = master.guess(words[idx]);
        if (val == 6) {
            return;
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == idx) {
                continue;
            }
            int tmp = match(words[idx], words[i]);
            if (tmp == val) {
                list.add(words[i]);
            }
        }

        while (list.size() > 1) {
            int rand = (int) (Math.random() * list.size());
            String selectedWord = list.get(rand);
            val = master.guess(selectedWord);
            if (val == 6) {
                return;
            }

            List<String> newList = new ArrayList<>();
            for (String word : list) {
                if (word.equals(selectedWord)) {
                    continue;
                }
                int tmp = match(selectedWord, word);
                if (tmp == val) {
                    newList.add(word);
                }
            }
            list = newList;
        }
        
        // If one word remains, then it the our final answer so guess it and complete the game sucessfully
        if (list.size() == 1) {
            master.guess(list.get(0));
        }
    }
}