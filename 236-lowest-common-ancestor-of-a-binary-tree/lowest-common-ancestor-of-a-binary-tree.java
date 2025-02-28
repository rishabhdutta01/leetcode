/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val == root.val || q.val == root.val) {
            return root;
        }

        boolean pRight = find(root.right, p);
        boolean qRight = find(root.right, q);

        if((pRight && !qRight) || (!pRight && qRight)) {
            return root;
        }
        if(!pRight && !qRight) {
            return lowestCommonAncestor(root.left, p, q); 
        } else {
            return lowestCommonAncestor(root.right, p, q); 
        }
    }

    boolean find(TreeNode curr, TreeNode x){
        if(curr == null) {
            return false;
        }
        if(curr.val == x.val) {
            return true;
        }
        return find(curr.left, x) || find(curr.right, x);
    }
}