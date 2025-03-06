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
        TreeNode curr = root;
        return build(curr, p, q);
    }

    TreeNode build(TreeNode curr, TreeNode x , TreeNode y){
        if(curr == null || curr == x || curr == y) {
            return curr;
        }
        
        TreeNode lcurr = build(curr.left, x, y);
        TreeNode rcurr = build(curr.right, x, y);

        if(lcurr == null && rcurr == null){
            return null;
        }else if((lcurr == x && rcurr == y) || (lcurr == y && rcurr == x)) {
            return curr;
        } else {
            if(lcurr == null) {
                return rcurr;
            }
            return lcurr;
        }
    }
}