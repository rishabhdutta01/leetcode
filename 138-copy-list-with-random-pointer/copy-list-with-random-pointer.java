/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        Node curr = head;
        Node prev = null;

        Map<Node, Node> map = new HashMap<>();
        Map<Node, Node> visited = new HashMap<>();
        while (curr != null) {
            if (visited.containsKey(curr)) {
                continue;
            }

            Node n = null;
            if (map.containsKey(curr))
                n = map.get(curr);
            else {
                n = new Node(curr.val);
                map.put(curr, n);
            }
            visited.put(curr, n);
            if (prev != null) {
                map.get(prev).next = n;
            }

            if (curr.random != null) {
                Node r = null;
                if (map.containsKey(curr.random))
                    r = map.get(curr.random);
                else {
                    r = new Node(curr.random.val);
                    map.put(curr.random, r);
                }
                n.random = r;
            }

            prev = curr;
            curr = curr.next;
        }

        return map.get(head);
    }
}