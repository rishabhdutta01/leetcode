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
    int sum=0;
    public int sumNumbers(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, 0);
        return sum;
    }

    void dfs(TreeNode curr, int num){
        if(curr == null) return;
        if(curr.left == null && curr.right == null){
            sum += num*10 + curr.val;
            return;
        }
        num = num*10 + curr.val;
        dfs(curr.left,num);
        dfs(curr.right,num);
    }
}