class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount==0) {
            return 0;
        }
        if(coins == null || coins.length==0){
            return -1;
        }
        int[] l = new int[amount+1];
        Arrays.fill(l,Integer.MAX_VALUE);
        l[0]=0;

 
        for(int i=0; i<amount+1; i++) {
            for(int j = 0; j< coins.length; j++) {
                if (i - coins[j] < 0){
                    continue;
                }
                if(l[i - coins[j]] == Integer.MAX_VALUE){
                   continue; 
                }
                l[i] = Math.min(l[i], 1 + l[i - coins[j]]);
            }
        }
        if(l[amount] == Integer.MAX_VALUE) {
            return -1;
        }
        return l[amount];
    }
}