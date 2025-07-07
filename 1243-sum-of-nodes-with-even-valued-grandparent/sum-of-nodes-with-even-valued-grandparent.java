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
    public int sumEvenGrandparent(TreeNode root) {
        dfs(root, 0, 0);
        return res; 
    }

    void dfs(TreeNode curr, int gp, int p){
        if(curr == null) return;
        
        if(gp == 2){
            res += curr.val;
        }
        
        if(curr.val % 2 == 0){
            dfs(curr.left, p + 1, 1);
            dfs(curr.right, p + 1, 1);
        } else{
            dfs(curr.left, p + 1, 0);
            dfs(curr.right, p + 1, 0);
        }
    }
}