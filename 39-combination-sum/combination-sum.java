class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(target,candidates,res, new ArrayList<>(), 0);
        return res;
    }

    public void backtrack(int target, int[] candidates, List<List<Integer>> res, List<Integer> seq, int start) {
        if(target < 0)
            return;
        else if(target == 0) {
            res.add(new ArrayList<>(seq));
        }
        else {
            for(int i = start; i< candidates.length;i++) {
                seq.add(candidates[i]);
                backtrack(target - candidates[i], candidates, res, seq, i);
                seq.remove(seq.size()-1);
            }
        }
    }
}