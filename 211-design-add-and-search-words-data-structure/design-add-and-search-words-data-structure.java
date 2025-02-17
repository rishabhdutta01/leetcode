class WordDictionary {
    Node root;

    class Node {
        char ch;
        Node[] children;
        boolean isWord;

        public Node(char ch) {
            this.ch = ch;
            this.children = new Node[26];
        }
    }

    public WordDictionary() {
        root = new Node('#');
    }
    
    public void addWord(String word) {
        if(word == null || word.length()==0){
            return;
        }
        Node curr = root;
        char[] arr = word.toCharArray();
        for(int i=0; i<arr.length; i++) {
            char ch = arr[i];
            if(curr.children[ch - 'a'] == null) {
                curr.children[ch - 'a'] = new Node(ch);
            }
            curr = curr.children[ch - 'a'];
        }
        curr.isWord=true;
    }
    
    public boolean search(String word) {
        if(word == null || word.length()==0){
            return false;
        }
        Node curr = root;
        char[] arr = word.toCharArray();
        return backtrack(arr, curr, 0);       
    }

    public boolean backtrack(char[] arr, Node curr, int index) {
        if(index==arr.length) {
            return curr.isWord;
        }

        char ch = arr[index];
        if (ch != '.') {
            if(curr.children[ch - 'a'] == null) {
                return false;
            }
            return backtrack(arr, curr.children[ch - 'a'], index+1);
        }

        for(int i=0; i<26;i++){
            if(curr.children[i] == null) {
                continue;
            }
            if(backtrack(arr, curr.children[i], index+1)) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */