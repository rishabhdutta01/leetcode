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
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        fnc(root);
        // max= Math.max(max,fnc(root));
        return max;
    }

    int fnc(TreeNode curr){
        if(curr == null){
            return 0;
        }
        int lsum = fnc(curr.left);
        int rsum = fnc(curr.right);
        max = Math.max(max, 
                    Math.max(lsum+curr.val, 
                    Math.max(rsum+curr.val, 
                    Math.max(lsum+rsum+curr.val, curr.val))));
        return Math.max(lsum+curr.val, 
                    Math.max(rsum+curr.val, curr.val));
    }
 }






// class Solution {
//     int res = Integer.MIN_VALUE;
//     public int maxPathSum(TreeNode root) {
//         if(root==null){
//             return 0;
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
//             if(curr.val<0){
//                 return 0;
//             }
//             return curr.val;
//         }

//         int l = find(curr.left);
//         int r = find(curr.right);
        
//         res = Math.max(Math.max(Math.max(res, r+curr.val),l+curr.val), l + curr.val + r);

//         if(l>=r){
//             if(l+curr.val > 0)
//                 return l+curr.val;
//             return 0;
//         }
//         if(r+curr.val > 0)
//             return r+curr.val;
//         return 0;
//     }
// }