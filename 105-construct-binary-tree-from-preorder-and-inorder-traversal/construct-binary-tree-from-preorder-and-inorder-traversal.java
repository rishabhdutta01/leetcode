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
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);

        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);

    }

    private TreeNode build(int[] preorder, int ps, int pe, int[] inorder, int is, int ie, Map<Integer, Integer> map) {

        if (is > ie || ps > pe)
            return null;

        TreeNode root = new TreeNode(preorder[ps]);
        int inRoot = map.get(preorder[ps]);
        int numsLeft = inRoot - is;

        root.left = build(preorder, ps + 1, ps + numsLeft, inorder, is, is + numsLeft - 1, map);
        root.right = build(preorder, ps + 1 + numsLeft, pe, inorder, inRoot + 1, ie, map);
        return root;

    }
}