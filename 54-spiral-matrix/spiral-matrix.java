class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int l = 0, r = col - 1;
        int u = 0, d = row - 1;

        List<Integer> res = new ArrayList<>();
        
        while (res.size() < row * col) {
            // Go right
            for (int j = l; j <= r; j++) {
                res.add(matrix[u][j]);
            }
            u++;
            if (u > d) break; // ← ADD THIS CHECK
            
            // Go down
            for (int i = u; i <= d; i++) {
                res.add(matrix[i][r]);
            }
            r--;
            if (r < l) break; // ← ADD THIS CHECK
            
            // Go left
            for (int j = r; j >= l; j--) {
                res.add(matrix[d][j]);
            }
            d--;
            if (d < u) break; // ← ADD THIS CHECK
            
            // Go up
            for (int i = d; i >= u; i--) {
                res.add(matrix[i][l]);
            }
            l++;
        }
        
        return res;
    }
}