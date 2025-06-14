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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, PriorityQueue<Pair<Integer, Integer>>> map = new HashMap<>();
        Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        q.add(new Pair(root, 0));
        int depth = 0;

        while(!q.isEmpty()){
            int size = q.size();
            for (int i=0;i<size;i++){
                Pair<TreeNode, Integer> p = q.poll();
                if(!map.containsKey(p.getValue())){
                    map.put(p.getValue(), new PriorityQueue<>((a,b)->a.getValue()==b.getValue() ? a.getKey()-b.getKey() : a.getValue()-b.getValue()));
                    min = Math.min(min, p.getValue());
                    max = Math.max(max, p.getValue());
                }

                map.get(p.getValue()).add(new Pair<>(p.getKey().val, depth));
                
                if(p.getKey().left != null) q.add(new Pair<>(p.getKey().left, p.getValue() - 1));
                if(p.getKey().right != null) q.add(new Pair<>(p.getKey().right, p.getValue() + 1));
            }
            depth++;
        }

        List<List<Integer>> res = new ArrayList<>();
        for(int i=min;i<=max;i++){
            PriorityQueue<Pair<Integer, Integer>> pq = map.get(i);
            List<Integer> l = new ArrayList<>();

            while(!pq.isEmpty()){
                l.add(pq.poll().getKey());
            }
            res.add(l);
        }
        return res;
    }
}