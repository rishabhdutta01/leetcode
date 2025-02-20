/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode curr = root;
        invert(curr);
        return root;
    }

    public void invert(TreeNode curr) {
        if(curr == null) {
            return;
        }
        TreeNode temp = curr.left;
        curr.left = curr.right;
        curr.right = temp;
        invert(curr.left);
        invert(curr.right);
    }
}