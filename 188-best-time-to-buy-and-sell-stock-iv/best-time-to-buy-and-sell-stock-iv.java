class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;

        int n = prices.length;
        int[][] dp = new int[k+1][n]; // k rows

        // Fill for max 1 and max 2 transactions
        for (int i = 1; i <= k; i++) {
            int maxDiff = -prices[0]; // Initialize with buying first stock
            for (int j = 1; j < n; j++) {
                // Either keep previous profit or sell today
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);

                // Update maxDiff for next iteration
                // maxDiff represents best buying opportunity considering previous transaction
                maxDiff = Math.max(maxDiff, dp[i - 1][j - 1] - prices[j]);
            }
        }

        return dp[k][n - 1]; // Return max profit with at most 2 transactions
    }
}