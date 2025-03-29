class Solution {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        boolean[] dp = new boolean[n];
        
        dp[0] = true;
        if(stones[1] != 1){
            return false;
        }
        dp[1] = true;
        
        List<Set<Integer>> jumps = new ArrayList<>(n);
        jumps.add(new HashSet<>());
        jumps.add(new HashSet<>());
        jumps.get(1).add(1);

        for(int i=2;i<n;i++){
            jumps.add(i, new HashSet<Integer>());
            for(int j=i-1;j>0;j--){
                if(dp[j]){
                    for(int k: jumps.get(j)){
                        if(stones[i] >= stones[j]+k-1 && stones[i] <= stones[j]+k+1){
                            dp[i] = true;
                            jumps.get(i).add(stones[i] - stones[j]);
                        }
                    }
                }   
            }
        }

        return dp[n-1];
    }
}