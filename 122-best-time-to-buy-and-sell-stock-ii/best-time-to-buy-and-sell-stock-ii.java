class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1 ){
            return 0;
        }
        if(prices.length == 2){
            return Math.max(0, prices[1]-prices[0]);
        }
        
        int n = prices.length;
        int[] dp = new int[n];

        for(int i = 1; i<n; i++){
            for(int j=0; j<i; j++){
                dp[i] = Math.max(dp[i], Math.max(0,prices[i] - prices[j]) + dp[j]);
            }            
        }

        return dp[n-1];
    }
}