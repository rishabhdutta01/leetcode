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
        if(root == null) return "N";
        StringBuilder sb = new StringBuilder();
        serializePreOrder(root, sb);
        return sb.toString();       
    }

    void serializePreOrder(TreeNode curr, StringBuilder sb){
        sb.append(curr.val);
        if(curr.left == null) sb.append(",N");
        else {
            sb.append(",");
            serializePreOrder(curr.left, sb);
        }

        if(curr.right == null) sb.append(",N");
        else {
            sb.append(",");
            serializePreOrder(curr.right, sb);
        }
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if("N".equals(data)) return null;

        List<String> l = Arrays.asList(data.split(","));
        Collections.reverse(l);
        return deserializePreOrder(new ArrayList<>(l));
    }

    TreeNode deserializePreOrder(List<String> l){
        if("N".equals(l.getLast())) {
            l.removeLast();
            return null;
        }

        TreeNode curr = new TreeNode(Integer.parseInt(l.getLast()));
        l.removeLast();

        curr.left = deserializePreOrder(l);
        curr.right = deserializePreOrder(l);
        return curr;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));