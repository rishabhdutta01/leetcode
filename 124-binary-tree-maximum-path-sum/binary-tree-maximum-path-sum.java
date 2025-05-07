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
    int res = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        find(root);
        return res;
    }
    
    private int find(TreeNode curr) {
        if (curr == null) {
            return 0;
        }
        
        // Get the maximum path sum from left and right subtrees
        int leftMax = Math.max(0, find(curr.left));
        int rightMax = Math.max(0, find(curr.right));
        
        // Update the global maximum by considering the path through current node
        res = Math.max(res, leftMax + curr.val + rightMax);
        
        // Return maximum single path (including current node)
        return Math.max(leftMax, rightMax) + curr.val;
    }
}

// class Solution {
//     int res = Integer.MIN_VALUE;
//     public int maxPathSum(TreeNode root) {
//         if(root==null){
//             return Integer.MIN_VALUE;
//         }
//         if(root.left == null && root.right == null){
//             return root.val;
//         }

//         TreeNode curr = root;
//         find(curr);
//         return res;
//     }

//     int find(TreeNode curr){
//         if(curr == null){
//             return 0;
//         }
//         if(curr.left == null && curr.right == null){
//             res = Math.max(res, curr.val);
//             if(curr.val<0) return 0;
//             return curr.val;
//         }

//         int l = find(curr.left);
//         int r = find(curr.right);
        
//         res = Math.max(Math.max(Math.max(Math.max(res,curr.val), r+curr.val),l+curr.val), l + curr.val + r);

//         if(l>=r) return Math.max(curr.val,l+curr.val);
//         return Math.max(curr.val,r+curr.val);
//     }
// }