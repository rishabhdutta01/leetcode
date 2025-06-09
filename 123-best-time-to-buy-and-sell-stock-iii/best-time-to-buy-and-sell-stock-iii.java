class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;

        int n = prices.length;
        int[][] dp = new int[3][n]; // dp[transactions][day] = max profit
        
        // Fill for max 1 and max 2 transactions
        for (int transactions = 1; transactions <= 2; transactions++) {
            // This tracks the best profit if we're currently holding a stock
            // (i.e., we bought it at some optimal earlier point)
            int bestProfitIfHolding = -prices[0]; // Buy on day 0
            
            for (int day = 1; day < n; day++) {
                // Option 1: Do nothing today (keep yesterday's profit)
                // Option 2: Sell today at prices[day], and add the best profit from holding
                dp[transactions][day] = Math.max(
                    dp[transactions][day - 1],           // Do nothing
                    prices[day] + bestProfitIfHolding    // Sell today
                );
                
                // Update: what's the best profit if we buy today?
                // We can buy today after completing (transactions-1) transactions yesterday
                bestProfitIfHolding = Math.max(
                    bestProfitIfHolding,                    // Keep previous best
                    dp[transactions - 1][day - 1] - prices[day]  // Buy today
                );
            }
        }
        
        return dp[2][n - 1]; // Max profit with at most 2 transactions
    }
}