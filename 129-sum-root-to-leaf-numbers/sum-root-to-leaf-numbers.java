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
        dfs(root, sb);
        return sum;
    }

    void dfs(TreeNode curr, StringBuilder sb){
        if(curr == null) return;
        if(curr.left == null && curr.right == null){
            sb.append(curr.val);
            sum += Integer.parseInt(sb.toString());
            sb.deleteCharAt(sb.length()-1);
            return;
        }
        sb.append(curr.val);
        dfs(curr.left,sb);
        dfs(curr.right,sb);
        sb.deleteCharAt(sb.length()-1);
        return;
    }
}