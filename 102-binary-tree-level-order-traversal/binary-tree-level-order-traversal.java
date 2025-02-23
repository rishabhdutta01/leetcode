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
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null){
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> outer = new ArrayList<>();
        TreeNode curr = root;
        traverse(curr, 0, outer);
        return outer;
    }

    public void traverse(TreeNode curr, int i, List<List<Integer>> outer){
            if(curr == null) {
                return;
            }
            List<Integer> inner;
            try{
                inner = outer.get(i);
            } catch(Exception e) {
                inner = new ArrayList<Integer>();
                outer.add(inner); 
            }

            inner.add(curr.val);

            traverse(curr.left, i+1, outer);
            traverse(curr.right, i+1, outer);
            return;
        }
}