/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
 //Optimal
 class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(subRoot == null) {
            return true;
        }
        if(root == null){
            return false;
        }
        return serialize(root).contains(serialize(subRoot));
    }

    public String serialize(TreeNode x) {
        if(x == null) {
            return "N";
        }
        return "("+ x.val + "," + serialize(x.left) + "," + serialize(x.right) + ")";
    }
}
 //Brute force
// class Solution {
//     public boolean isSubtree(TreeNode root, TreeNode subRoot) {
//         if(subRoot == null) {
//             return true;
//         }
//         if(root == null){
//             return false;
//         }
//         if(isSame(root,subRoot)){
//             return true;
//         }
//         return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
//     }

//     public boolean isSame(TreeNode r, TreeNode sr) {
//         if(r == null && sr == null) {
//             return true;
//         } else if(r == null || sr == null) {
//             return false;
//         }
//         if(r.val == sr.val) {
//             return isSame(r.left, sr.left) && isSame(r.right, sr.right);
//         }else{
//             return false;
//         }
//     }
// }