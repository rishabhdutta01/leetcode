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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);

        return build(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode build(int[] postorder, int ps, int pe, int[] inorder, int is, int ie, Map<Integer, Integer> map) {
        if (is > ie || ps > pe)
            return null;

        TreeNode root = new TreeNode(postorder[pe]);
        int inRoot = map.get(postorder[pe]);
        int numsLeft = inRoot - is;

        root.left = build(postorder, ps, ps + numsLeft - 1, inorder, is, is + numsLeft - 1, map);
        root.right = build(postorder, ps + numsLeft, pe - 1, inorder, inRoot + 1, ie, map);
        return root;

    }
}