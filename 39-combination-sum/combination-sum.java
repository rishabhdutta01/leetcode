class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        fnc(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    void fnc(int[] candidates, int idx, int k, List<Integer> l, List<List<Integer>> res){
        if(k == 0){
            res.add(new ArrayList<>(l));
        }
        if(k<0){
            return;
        }

        for(int i=idx;i<candidates.length; i++){
            if(k-candidates[i] < 0) return;
            l.add(candidates[i]);
            fnc(candidates, i, k-candidates[i], l, res);
            l.removeLast();
        }
    }
}