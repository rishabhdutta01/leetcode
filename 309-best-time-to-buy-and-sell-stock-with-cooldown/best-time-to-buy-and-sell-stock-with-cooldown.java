class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        
        int n = prices.length;
        
        // Define three states:
        // hold[i]: max profit if we are holding stock at day i
        // sold[i]: max profit if we just sold stock at day i
        // cool[i]: max profit if we are in cooldown at day i
        int[] hold = new int[n];
        int[] sold = new int[n];
        int[] cool = new int[n];
        
        // Initialize first day
        hold[0] = -prices[0];  // bought the stock
        sold[0] = 0;           // cannot sell on first day
        cool[0] = 0;           // nothing to cooldown from
        
        // Fill the arrays
        for (int i = 1; i < n; i++) {
            // If holding stock, either kept from previous day or bought after cooldown
            hold[i] = Math.max(hold[i-1], cool[i-1] - prices[i]);
            
            // If just sold, must have held stock previous day
            sold[i] = hold[i-1] + prices[i];
            
            // If in cooldown, either continued cooldown or came from sold
            cool[i] = Math.max(cool[i-1], sold[i-1]);
        }
        
        // Return maximum of sold or cool state
        return Math.max(sold[n-1], cool[n-1]);
    }
}