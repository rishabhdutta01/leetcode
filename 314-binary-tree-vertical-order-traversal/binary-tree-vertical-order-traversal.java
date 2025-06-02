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
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null) return Collections.emptyList();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Queue<Pair<TreeNode,Integer>> q = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();

        q.add(new Pair<>(root, 0));

        while(!q.isEmpty()){
            Pair<TreeNode,Integer> p = q.poll();
            TreeNode n = p.getKey();
            int d = p.getValue();

            min = Math.min(min, d);
            max = Math.max(max, d);

            if(!map.containsKey(d)){
                map.put(d, new ArrayList<>());
            }
            map.get(d).add(n.val);

            if(n.left != null) q.add(new Pair<>(n.left, d-1));
            if(n.right != null) q.add(new Pair<>(n.right, d+1));
        }

        List<List<Integer>> res = new ArrayList<>(max-min+1);

        for(int i=min;i<=max;i++){
            res.add(map.get(i));
        }
        return res;
    }
}