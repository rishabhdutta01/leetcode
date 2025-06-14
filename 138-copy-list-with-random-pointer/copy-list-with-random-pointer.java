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
        if(head == null) return null;
        Node curr = head;
        while (curr != null) {
            Node n = new Node(curr.val);
            n.next = curr.next;
            curr.next = n;
            curr = n.next;
        }

        curr = head;
        while (curr != null) {
            if(curr.random != null){
                curr.next.random = curr.random.next;
            }  
            curr = curr.next.next;
        }

                curr = head;
        Node newHead = curr.next;
        Node newCurr = newHead;
        while (curr != null) {
            curr.next = newCurr.next; // Restore original list's next pointer
            
            // Set the next pointer for the copied node, handle the end of the list
            if (newCurr.next != null) { 
                newCurr.next = newCurr.next.next;
            } else {
                newCurr.next = null; // Set the last copied node's next to null
            }
            
            curr = curr.next; // Move to the next original node
            newCurr = newCurr.next; // Move to the next copied node
        }
        return newHead;
    }
}

// class Solution {
//     public Node copyRandomList(Node head) {
//         Node curr = head;
//         Node prev = null;

//         Map<Node, Node> map = new HashMap<>();
//         Map<Node, Node> visited = new HashMap<>();
//         while (curr != null) {
//             if (visited.containsKey(curr)) {
//                 continue;
//             }

//             Node n = null;
//             if (map.containsKey(curr))
//                 n = map.get(curr);
//             else {
//                 n = new Node(curr.val);
//                 map.put(curr, n);
//             }
//             visited.put(curr, n);
//             if (prev != null) {
//                 map.get(prev).next = n;
//             }

//             if (curr.random != null) {
//                 Node r = null;
//                 if (map.containsKey(curr.random))
//                     r = map.get(curr.random);
//                 else {
//                     r = new Node(curr.random.val);
//                     map.put(curr.random, r);
//                 }
//                 n.random = r;
//             }

//             prev = curr;
//             curr = curr.next;
//         }

//         return map.get(head);
//     }
// }