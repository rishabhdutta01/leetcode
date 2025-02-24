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
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) {
            return 0;
        }
        List<Integer> l = new ArrayList<>();
        TreeNode curr = root;
        traverse(curr, l, k);
        return l.get(k-1);
    }

    void traverse(TreeNode curr, List<Integer> l, int k) {
        if(curr == null){
            return;
        }
        traverse(curr.left, l, k);
        l.add(curr.val);
        traverse(curr.right, l, k);
    }
}