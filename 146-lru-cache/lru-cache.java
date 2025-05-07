class LRUCache {
    class Node{
        int key;
        int value;
        Node next;
        Node prev;
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    Node tail;
    Map<Integer, Node> m;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        m = new HashMap<>();
        head = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE); 
        tail = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!m.containsKey(key)){
            return -1;
        }

        Node n = m.get(key);
        remove(n);
        add(n);
        return n.value;
    }
    
    public void put(int key, int value) {
        if(m.containsKey(key)){
            remove(m.get(key));
        }

        Node n = new Node(key, value);
        m.put(key, n);
        add(n);

        if(m.size() > capacity){
            Node toRemove = head.next;
            remove(toRemove);
            m.remove(toRemove.key);
        }
    }

    void remove(Node n){
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    void add(Node n){
        n.next = tail;
        n.prev = tail.prev;
        tail.prev = n;
        n.prev.next = n;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */