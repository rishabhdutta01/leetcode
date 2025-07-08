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
    public String getDirections(TreeNode root, int startValue, int destValue) {
        if(startValue == destValue) return "";

        Set<Integer> ss = new HashSet<>();
        Set<Integer> ds = new HashSet<>();
        List<int[]> sl = new ArrayList<>();
        List<int[]> dl = new ArrayList<>();

        dfs(root, startValue, ss, sl);
        dfs(root, destValue, ds, dl);

        StringBuilder sb = new StringBuilder();

        int i = sl.size() - 1;

        while(i >=0){
            if(!ds.contains(sl.get(i)[0])){
                i--;
            } else{
                break;
            }
            sb.append("U");
        }

        boolean found = false;
        for(int[] e: dl){
                if(found){
                    if(e[1] == 1) sb.append("L");
                    else if(e[1] == 2) sb.append("R");
                }

                if(e[0] == sl.get(i)[0]) {
                    if(e[1] == 1) sb.append("L");
                    else if(e[1] == 2) sb.append("R");
                    found = true;
                }
                else continue;
            }
        return sb.toString();
    }

    boolean dfs(TreeNode curr, int val, Set<Integer> s, List<int[]> l) {
        if (curr == null)
            return false;
        
        s.add(curr.val);

        if (curr.val == val){
            l.add(new int[] { curr.val, 0 });
            return true;
        }
            

        
        l.add(new int[] { curr.val, 1 });
        if (dfs(curr.left, val, s, l))
            return true;

        l.removeLast();
        l.add(new int[] { curr.val, 2 });
        if (dfs(curr.right, val, s, l))
            return true;

        s.remove(curr.val);
        l.removeLast();
        return false;
    }
}