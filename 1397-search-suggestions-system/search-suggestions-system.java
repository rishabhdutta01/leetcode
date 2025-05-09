// Custom class Trie with function to get 3 words starting with given prefix
class Trie {

    // Node definition of a trie
    class Node {
        boolean isWord = false;
        List<Node> children = Arrays.asList(new Node[26]);
    };
    Node Root, curr;
    List<String> resultBuffer;

    // Runs a DFS on trie starting with given prefix and adds all the words in the resultBuffer, limiting result size to 3
    void dfsWithPrefix(Node curr, String word) {
        if (resultBuffer.size() == 3)
            return;
        if (curr.isWord)
            resultBuffer.add(word);

        // Run DFS on all possible paths.
        for (char c = 'a'; c <= 'z'; c++)
            if (curr.children.get(c - 'a') != null)
                dfsWithPrefix(curr.children.get(c - 'a'), word + c);
    }
    Trie() {
        Root = new Node();
    }

    // Inserts the string in trie.
    void insert(String s) {

        // Points curr to the root of trie.
        curr = Root;
        for (char c : s.toCharArray()) {
            if (curr.children.get(c - 'a') == null)
                curr.children.set(c - 'a', new Node());
            curr = curr.children.get(c - 'a');
        }

        // Mark this node as a completed word.
        curr.isWord = true;
    }
    List<String> getWordsStartingWith(String prefix) {
        curr = Root;
        resultBuffer = new ArrayList<String>();
        // Move curr to the end of prefix in its trie representation.
        for (char c : prefix.toCharArray()) {
            if (curr.children.get(c - 'a') == null)
                return resultBuffer;
            curr = curr.children.get(c - 'a');
        }
        dfsWithPrefix(curr, prefix);
        return resultBuffer;
    }
};
class Solution {
    List<List<String>> suggestedProducts(String[] products,
                                         String searchWord) {
        Trie trie = new Trie();
        List<List<String>> result = new ArrayList<>();
        // Add all words to trie.
        for (String w : products)
            trie.insert(w);
        String prefix = new String();
        for (char c : searchWord.toCharArray()) {
            prefix += c;
            result.add(trie.getWordsStartingWith(prefix));
        }
        return result;
    }
};
// class Solution {
//     class Node {
//         Character ch;
//         Map<Character, Node> children;
//         String word;

//         Node(Character ch) {
//             this.ch = ch;
//             children = new TreeMap<>();
//         }
//     }

//     public List<List<String>> suggestedProducts(String[] products, String searchWord) {
//         //build trie
//         Node root = new Node('/');
//         for (String p : products) {
//             Node curr = root;
//             for (int i = 0; i < p.length(); i++) {
//                 Character ch = p.charAt(i);
//                 if (!curr.children.containsKey(ch)) {
//                     curr.children.put(ch, new Node(ch));
//                 }
//                 curr = curr.children.get(ch);
//             }
//             curr.word = p;
//         }

//         List<List<String>> res = new ArrayList<>();
//         Node curr = root;
//         for (int i = 0; i < searchWord.length(); i++) {
//             char ch = searchWord.charAt(i);
//             List<String> arr = new ArrayList<>();
//             if (!curr.children.containsKey(ch)) {
//                 for(int j=i;j<searchWord.length(); j++){
//                     res.add(new ArrayList<>());
//                 }
//                 break;
//             }
//             curr = curr.children.get(ch);
//             dfs(curr, arr);
//             res.add(arr);
//         }
//         return res;
//     }

//     void dfs(Node curr, List<String> arr) {
//         if (arr.size() >= 3) { // Check size before doing anything
//             return;
//         }

//         if (curr.word != null) {
//             arr.add(curr.word);
//         }

//         for (Character ch : curr.children.keySet()) {
//             if (arr.size() >= 3)
//                 break; // Break the loop if we have 3 words
//             dfs(curr.children.get(ch), arr);
//         }
//     }
// }

// class Solution {
//     public List<List<String>> suggestedProducts(String[] products, String searchWord) {
//         Arrays.sort(products);

//         List<List<String>> res = new ArrayList<>();
//         List<String> p = Arrays.asList(products);
//         for(int i=0;i<searchWord.length();i++){
//             List<String> newp = new ArrayList<>();
//             for(int j=0;j<p.size();j++){
//                 if(i>=p.get(j).length()){
//                     continue;
//                 }
//                 if(searchWord.charAt(i) == p.get(j).charAt(i)){
//                     newp.add(p.get(j));
//                 }
//             }
//             p = newp;

//             res.add(p.subList(0, Math.min(3, p.size())));
//         }
//         return res;
//     }
// }