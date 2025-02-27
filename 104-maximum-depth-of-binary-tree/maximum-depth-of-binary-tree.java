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

    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return traverse(root, 0);
    }

    int traverse(TreeNode curr, int h) {
        if (curr == null) {
            return h;
        }
        int leftDepth = traverse(curr.left, h + 1);
        int rightDepth = traverse(curr.right, h + 1);
        return Math.max(leftDepth, rightDepth);
    }

}