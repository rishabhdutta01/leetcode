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
    public int rangeSumBST(TreeNode root, int low, int high) {
        return dfs(root,low,high);
    }

    int dfs(TreeNode curr, int low, int high){
        if(curr == null) return 0;
        if(curr.left == null && curr.right == null){
            if(curr.val>=low && curr.val<=high) return curr.val;
            else return 0;
        } 
        
        int l=0,r=0;
        if(curr.val>=low){
            l=dfs(curr.left,low,high);
        }

        if(curr.val<=high){
            r=dfs(curr.right,low,high);
        }

        if(curr.val>=low && curr.val<=high) return curr.val+l+r;
        else return l+r;
    }
}