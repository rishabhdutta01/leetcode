class Trie {
    Node head;

    public Trie() {
        this.head = new Node();       
    }
    
    public void insert(String word) {
        char[] arr = word.toCharArray();
        Node curr = head;
        for(int i=0;i<arr.length;i++) {
            if(curr.children[arr[i]-'a'] == null){
                curr.children[arr[i]-'a'] = new Node(arr[i]);
            }
            curr = curr.children[arr[i]-'a'];
        }
        curr.isWord = true;
    }
    
    public boolean search(String word) {        
        char[] arr = word.toCharArray();
        Node curr = head;

        for(int i=0;i<arr.length;i++) {
            if(curr.children[arr[i]-'a'] == null) {
                return false;
            }
            curr = curr.children[arr[i]-'a'];
        }
        return curr.isWord;        
    }
    
    public boolean startsWith(String prefix) {        
        char[] arr = prefix.toCharArray();
        Node curr = head;
        boolean res = false;

        for(int i=0;i<arr.length;i++) {
            if(curr.children[arr[i]-'a'] == null) {
                return false;
            }
            curr = curr.children[arr[i]-'a'];
        }
        return true;        
    }

    class Node {
        char ch;
        Node[] children;
        boolean isWord;

        Node() {
            this.ch = '#';
            this.children = new Node[26];
            Arrays.fill(this.children,null);
            this.isWord = false;
        }

        Node(char ch) {
            this.ch = '#';
            this.children = new Node[26];
            Arrays.fill(this.children,null);
            this.isWord = false;
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */