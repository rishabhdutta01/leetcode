/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null){
            return null;
        }

        Map<Node, Node> m = new HashMap<>();
        Queue<Node> que = new LinkedList<>();

        Node head = new Node(node.val);
        que.offer(node);
        m.put(node, head);

        while(!que.isEmpty()){
            node = que.poll();
            for(Node n: node.neighbors) {
                if(!m.containsKey(n)){
                    m.put(n, new Node(n.val));

                    que.offer(n);
                }
                m.get(node).neighbors.add(m.get(n));                
            } 
        }
        return head;        
    }
}