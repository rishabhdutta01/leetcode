class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return new ArrayList<>();
        
        // BFS to build parent-child relationships
        Map<String, List<String>> parents = new HashMap<>();
        Set<String> currentLevel = new HashSet<>();
        Set<String> nextLevel = new HashSet<>();
        
        currentLevel.add(beginWord);
        wordSet.remove(beginWord);
        boolean found = false;
        
        while (!currentLevel.isEmpty() && !found) {
            // Remove words from current level to avoid cycles
            wordSet.removeAll(currentLevel);
            
            for (String word : currentLevel) {
                for (int i = 0; i < word.length(); i++) {
                    char[] chars = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String newWord = new String(chars);
                        
                        if (wordSet.contains(newWord)) {
                            nextLevel.add(newWord);
                            parents.computeIfAbsent(newWord, k -> new ArrayList<>()).add(word);
                            
                            if (newWord.equals(endWord)) {
                                found = true;
                            }
                        }
                    }
                }
            }
            
            currentLevel = nextLevel;
            nextLevel = new HashSet<>();
        }
        
        // DFS to build all paths
        List<List<String>> result = new ArrayList<>();
        if (found) {
            dfs(endWord, beginWord, parents, new ArrayList<>(), result);
        }
        
        return result;
    }
    
    private void dfs(String word, String beginWord, Map<String, List<String>> parents, 
                     List<String> path, List<List<String>> result) {
        path.add(word);
        
        if (word.equals(beginWord)) {
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath);
            result.add(validPath);
        } else {
            for (String parent : parents.getOrDefault(word, new ArrayList<>())) {
                dfs(parent, beginWord, parents, path, result);
            }
        }
        
        path.remove(path.size() - 1); // backtrack
    }
}