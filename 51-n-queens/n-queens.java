class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> arr = new ArrayList();

        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                arr.get(i).add(".");
            }
        }

        boolean[] diags = new boolean[2*n-1];
        boolean[] cols = new boolean[n];
        List<List<String>> res = new ArrayList();

        backtrack(arr, n, 0, diags, cols, res);
        return res;
    }

    void backtrack(List<List<String>> arr, int n, int idx, boolean[] diags, boolean[] cols, List<List<String>> res){
        if(idx >= n){
            List<String> arr1= new ArrayList();

            for(int i=0;i<n;i++){
                arr1.add(String.join("",arr.get(i)));
            }
            res.add(arr1);
            
        }

        for(int j=0;j<n;j++){
            if(cols[j] || diags[idx+j]){
                continue;
            }
            int r = idx-1;
            int c = j-1;
            boolean found = false;
            while(r>=0 && c>=0){
                if("Q".equals(arr.get(r).get(c))) {
                    found  = true;
                    break;
                }
                r--;
                c--;
            }
            if(found) continue;

            cols[j] = true;
            diags[idx+j] = true;
            arr.get(idx).set(j, "Q");

            backtrack(arr,n,idx+1,diags,cols, res);

            arr.get(idx).set(j, ".");
            cols[j] = false;
            diags[idx+j] = false;
        }
    }
}