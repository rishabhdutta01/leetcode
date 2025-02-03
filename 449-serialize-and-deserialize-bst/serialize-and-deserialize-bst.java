/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null){
            return "";
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);

        StringBuilder s= new StringBuilder();
        s.append(root.val);
        
        while(!q.isEmpty()) {
            TreeNode curr = q.poll();
            if(curr == null){
                s.append(",");
                s.append("#");
                continue;
            }
            s.append(",");
            s.append(curr.val);
            q.add(curr.left);
            q.add(curr.right);
        }
        return s.toString();
    }



    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data==null || data.length() == 0){
            return null;
        }

        String[] arr = data.split(",");

        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        q.add(root);

        for(int i = 1;i<arr.length;i++) {
            TreeNode curr = q.poll();
            if(!"#".equals(arr[i])){
                TreeNode left = new TreeNode(Integer.parseInt(arr[i]));
                curr.left = left;
                q.add(left);
            }
            
            if(!"#".equals(arr[++i])){
                TreeNode right = new TreeNode(Integer.parseInt(arr[i]));
                curr.right = right;
                q.add(right);
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;