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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    int dfs(TreeNode curr, List<List<Integer>> res){
        if(curr == null){
            return 0;
        }
        int l = dfs(curr.left, res);
        int r = dfs(curr.right, res);
        int pos = Math.max(l,r) + 1;
        if(res.size() < pos){
            res.add(new ArrayList<>());
        }
        res.get(pos-1).add(curr.val);
        return pos;
    }
}