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
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        TreeNode curr = root;
        fnc(curr);
        return res;
    }

    int fnc(TreeNode curr){
        if(curr == null) return 0;
        if(curr.left == null && curr.right == null) return 1;

        int l = fnc(curr.left);
        int r = fnc(curr.right);
        res = Math.max(res, l+r);
        return 1 + Math.max(l,r);
    }
}