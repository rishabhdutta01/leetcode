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
    public TreeNode trimBST(TreeNode root, int low, int high) {
        root = dfs(root, low, high);  
        return root;
    }

    TreeNode dfs(TreeNode curr, int low, int high){
        if(curr == null) return null;

        if(curr.left == null && curr.right == null){
            if(curr.val<low || curr.val>high){
                return null;
            } else{
                return curr;
            }
        }

        if(curr.val < low){
            return dfs(curr.right,low,high);
            
        } else{
            curr.left = dfs(curr.left,low,high);
        }

        if(curr.val > high){
            return  dfs(curr.left,low,high);
            
        } else{
            curr.right = dfs(curr.right,low,high);
        }
        return curr;
    }
}