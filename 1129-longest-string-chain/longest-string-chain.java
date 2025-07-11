class Solution {
    public int longestStrChain(String[] words) {
        Map<String, Integer> chains = new HashMap<>();  // Stores the max chain length for each word
        Arrays.sort(words, (a, b) -> a.length() - b.length());  // Sort words by length

        for (String word : words) {
            chains.put(word, 1);  // Initialize the chain length for the current word
            if(word.length() == 1){
                continue;
            }

            for (int i = 0; i < word.length(); i++) {
                String pred = word.substring(0, i) + word.substring(i + 1);  // Generate predecessor by removing one character
                if (chains.containsKey(pred)) {
                    chains.put(word, Math.max(chains.getOrDefault(word, 0), chains.get(pred) + 1));
                }
            }
        }

        int maxChainLength = chains.values().stream().mapToInt(Integer::intValue).max().orElse(0);
        return maxChainLength;        
    }
}