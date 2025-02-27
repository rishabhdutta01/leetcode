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
        int h = 0;
        int[] res = new int[]{-1};
        TreeNode curr = root;
        traverse(curr, h, res);
        return res[0];
    }

    void traverse(TreeNode curr, int h, int[] res) {
        if(curr == null){
            res[0] = Math.max(res[0], h);
            return;
        }
        traverse(curr.left, h+1, res);
        traverse(curr.right, h+1, res);
    }

}