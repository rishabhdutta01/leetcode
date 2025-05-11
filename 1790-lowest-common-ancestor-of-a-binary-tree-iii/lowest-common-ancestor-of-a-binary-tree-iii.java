/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> pset = new HashSet<>();
        
        // Add all ancestors of p (including p) to set
        Node curr = p;
        while (curr != null) {
            pset.add(curr);
            curr = curr.parent;
        }
        
        // Check q and its ancestors
        curr = q;
        while (curr != null) {
            if (pset.contains(curr)) {
                return curr;  // First common ancestor found is the LCA
            }
            curr = curr.parent;
        }
        
        return null;
    }
}