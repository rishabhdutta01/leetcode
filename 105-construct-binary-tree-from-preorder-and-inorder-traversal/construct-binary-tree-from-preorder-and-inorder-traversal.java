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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0 && inorder.length == 0) return null;

        TreeNode root = new TreeNode(preorder[0]);

        int ridx = 0;
        for(int i=0;i<inorder.length;i++){
            if(inorder[i] == preorder[0]) {
                ridx = i;
                break;
            }
        }

        root.left = buildTree(Arrays.copyOfRange(preorder, 1, 1+ridx), Arrays.copyOfRange(inorder, 0, ridx));
        root.right = buildTree(Arrays.copyOfRange(preorder, 1+ridx, preorder.length), Arrays.copyOfRange(inorder, ridx+1, inorder.length));

        return root;
    }
}