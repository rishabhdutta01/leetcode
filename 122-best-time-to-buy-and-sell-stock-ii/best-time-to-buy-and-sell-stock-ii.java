class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1 ){
            return 0;
        }
        if(prices.length == 2){
            return Math.max(0, prices[1]-prices[0]);
        }
        
        int n = prices.length;
        int res = 0;
        int prev = 0;
        int l=0;
        int r=1;
        while(r<n){
            if(prices[l] >= prices[r]){
                l = r;
                r++;
            } else{
                prev = Math.max(prev, prices[r] - prices[l]);
                r++;
                if(r<n){
                    if(prices[r-1] > prices[r]){
                        res+=prev;
                        prev=0;
                        l=r;
                        r++;
                    }
                }
            }
        }

        return res+prev;
    }
}